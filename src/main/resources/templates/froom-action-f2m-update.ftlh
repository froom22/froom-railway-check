<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="content-type" content="text/html; char-set: UTF-8">
    <title>Froom Received Order # ${froomOrderID}</title>
</head>
<body>
<div><b>Dear
        <#if name??>${name} </#if> (Manufacturer),</b> <br>
</div>

<div style="margin-top: 10px; text-align:center"><b>The Order for <#if froomOrderID??>${froomOrderID}</#if> is recieved at the Froom Location and will be notified to the Customer as well
    </b></div>
<br>



<hr>

<small>
    This e-mail is an automated confirmation that we have received details regarding the order
    you have placed with our merchant partner. Please note that you cannot reply to this email.<br>
    Please reach out to your merchant for any questions regarding your order. For queries
    regarding Froom location, please email us at <i>buywithfroom@gmail.com</i>
    <br><br>
    Froom Inc.<br>
    All rights reserved
</small>
</body>
</html>