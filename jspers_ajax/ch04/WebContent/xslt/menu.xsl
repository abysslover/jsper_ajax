<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:jwide="http://javawide.com/menu"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xsi:schemaLocation="http://javawide.com/menu ../xsd/menu.xsd">
	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
				<title>Hello XSLT Menu</title>
			</head>
			<body>
				<div id="menu">
					<ol>
						<xsl:apply-templates select="jwide:menus" />
					</ol>
				</div>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="jwide:menus">
		<xsl:apply-templates select="jwide:menu" />
	</xsl:template>
	<xsl:template match="jwide:menu">
		<li>
			<div onmouseover="this.style.backgroundColor='{@hovercolor}';"
				onmouseout="this.style.backgroundColor='{@blurcolor}';">
				<xsl:value-of select="@title" />
			</div>
			<ol>
				<xsl:apply-templates select="jwide:menu" />
			</ol>
		</li>
	</xsl:template>
</xsl:stylesheet>