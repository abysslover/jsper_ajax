<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="HelloSmartClientWeb._Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Hello Smart Client</title>
    <script language="javascript" type="text/javascript" src="js/prototype.js"></script>
    <script language="javascript" type="text/javascript" src="js/page.js"></script>
    <script language="javascript" type="text/javascript" src="js/activator.js"></script>
</head>
<body>
    <div>
        <input type="text" id="txtPage" />
        <input type="button" id="btnPage" value="페이지 변경" />
        <script language="javascript" type="text/javascript">newHelloSmartClient();</script>
    </div>
</body>
</html>