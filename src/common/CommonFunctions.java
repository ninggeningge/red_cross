package common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ningge on 15/12/31.
 */
public class CommonFunctions {

    static public String md5(String original){

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(original.getBytes());
                byte b[] = md.digest();

                int i;

                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if(i<0) i+= 256;
                    if(i<16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }

                return buf.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


        return "";//错误


    }

    public static final String escapeHTML(String s){
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                case '&': sb.append("&amp;"); break;
                case '"': sb.append("&quot;"); break;
                case 'à': sb.append("&agrave;");break;
                case 'À': sb.append("&Agrave;");break;
                case 'â': sb.append("&acirc;");break;
                case 'Â': sb.append("&Acirc;");break;
                case 'ä': sb.append("&auml;");break;
                case 'Ä': sb.append("&Auml;");break;
                case 'å': sb.append("&aring;");break;
                case 'Å': sb.append("&Aring;");break;
                case 'æ': sb.append("&aelig;");break;
                case 'Æ': sb.append("&AElig;");break;
                case 'ç': sb.append("&ccedil;");break;
                case 'Ç': sb.append("&Ccedil;");break;
                case 'é': sb.append("&eacute;");break;
                case 'É': sb.append("&Eacute;");break;
                case 'è': sb.append("&egrave;");break;
                case 'È': sb.append("&Egrave;");break;
                case 'ê': sb.append("&ecirc;");break;
                case 'Ê': sb.append("&Ecirc;");break;
                case 'ë': sb.append("&euml;");break;
                case 'Ë': sb.append("&Euml;");break;
                case 'ï': sb.append("&iuml;");break;
                case 'Ï': sb.append("&Iuml;");break;
                case 'ô': sb.append("&ocirc;");break;
                case 'Ô': sb.append("&Ocirc;");break;
                case 'ö': sb.append("&ouml;");break;
                case 'Ö': sb.append("&Ouml;");break;
                case 'ø': sb.append("&oslash;");break;
                case 'Ø': sb.append("&Oslash;");break;
                case 'ß': sb.append("&szlig;");break;
                case 'ù': sb.append("&ugrave;");break;
                case 'Ù': sb.append("&Ugrave;");break;
                case 'û': sb.append("&ucirc;");break;
                case 'Û': sb.append("&Ucirc;");break;
                case 'ü': sb.append("&uuml;");break;
                case 'Ü': sb.append("&Uuml;");break;
                case '®': sb.append("&reg;");break;
                case '©': sb.append("&copy;");break;
                case '€': sb.append("&euro;"); break;
                // be carefull with this one (non-breaking whitee space)
                case ' ': sb.append("&nbsp;");break;

                default:  sb.append(c); break;
            }
        }
        return sb.toString();
    }

    public static void error(HttpServletRequest request,HttpServletResponse response, String title , String message, String url){
        request.setAttribute("title" , title);
        request.setAttribute("message", message);
        request.setAttribute("url" , url);

        try{
            request.getRequestDispatcher("/view/error_message.jsp").forward(request,response);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean checkLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("isLogin")!= null && (boolean)session.getAttribute("isLogin")&& session.getAttribute("username") != null){
            return true;
        }
        else{
            return false;
        }
    }
}
