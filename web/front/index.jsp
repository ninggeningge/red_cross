<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>

	<!-- Meta setup -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui">

	<!-- Title -->
	<title>红十字会</title>

	<!-- Include Webplate -->
	<script id="webplate-stack" src="/front/js/stack.js"></script>
	<style>
		#statistics{
			border-top: 1px solid #e6e6e6;
		}
		#components{
			border-top: 1px solid #e6e6e6;
		}
	</style>
	<link rel="shortcut icon" href="/view/img/red_cross.png">

</head>
<body style="display:none;" data-icon-font="icomoon" data-ui="quill" data-formplate-colour="grey-blue">



<!-- Title bar -->
<div class="title-bar-clear">

	<ul class="right-links navigation">
		<li><a href="#text" class="basic">余额查询</a></li>
		<li><a href="#components" class="basic">流水查询</a></li>
		<li><a href="#statistics" class="basic">统计信息</a></li>
	</ul>

	<a href="#" class="navigation-trigger icon-only icon-menu"></a>

	<div class="logo">红十字会</div>

</div>

<!-- Header -->
<header class="full">

	<div class="title-tight"><div class="inner">
			<h2>欢迎来到红十字会网站</h2>
		</div>
	</div>
	<a href="#" class="scroll-down icon-angle-down"></a>

	<div class="bg-image"></div>

</header>
<!-- Text -->
<section id="text" class="line"><div class="contain">
	<h3>余额信息</h3>

	<blockquote>
        <p>本站自建站以来,共接收捐款<b>${d_times}</b>次,共资助<b>${s_times}</b>次,</p>
		<p>目前余额<b>${total}</b>元</p>
	</blockquote>

</div></section>

<!-- Basic elements -->
<section id="components"><div class="contain">

	<h3>流水查询</h3>
	<p>&nbsp;</p>

	<div class="line">
		<div class="contain">
			<form action="/query" method="get">
				<div class="formplate">
					<label for="start">开始时间</label>
					<input type="date" name="start" value="" id="start">
				</div>
				<div class="formplate">
					<label for="end">结束时间</label>
					<input type="date" name="end" value="" id="end">
				</div>
				<div class="formplate">
					<label for="name">捐款人</label>
					<input type="text" name="name" value="" id="name">
				</div>
				<div id="type_container">
					<label for="type_container">类型选择</label>
					<div class="formplate">
						<input type="radio" name="type" value="1" id="radio-1">
						<label for="radio-1">捐款查询</label>
					</div>
					<div class="formplate">
						<input type="radio" name="type" value="2" id="radio-2">
						<label for="radio-2">资助查询</label>

					</div>
					<input type="hidden" name="action" value="query">
				</div>
				<p>&nbsp;</p>
				<div class="formplate middle">
					<!--<button type="submit">查询</button>-->
					<input type="submit" value="查询"  style="background:#00a6fc;color: #ffffff; ">
				</div>
			</form>
		</div>
	</div>

	<p>&nbsp;</p>


</div></section>

<!-- Text -->
<section id="statistics" class="line"><div class="contain">
	<form action="/statistics" method="get">
		<h3>统计信息</h3>
		<p>&nbsp;</p>
		<div class="formplate">
			<label for="start2">开始时间</label>
			<input type="date" name="start"  id="start2">
		</div>
		<div class="formplate">
			<label for="end2">结束时间</label>
			<input type="date" name="end"  id="end2">
		</div>
		<p>&nbsp;</p>
		<div class="formplate">
			<input type="submit" value="查询"  style="background:#00a6fc;color: #ffffff; ">
		</div>
	</form>

</div>
</section>
</body>
</html>