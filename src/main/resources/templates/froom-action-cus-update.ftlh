<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="content-type" content="text/html; char-set: UTF-8">
    <title>Froom Received Your Order # ${froomOrderID}</title>
</head>
<body>
<div><b>Dear
        <#if name??>${name} </#if> <#if userName??> ${userName}</#if>,</b> <br>
</div>

<div style="margin-top: 10px; text-align:center"><b>The Order for <#if froomOrderID??>${froomOrderID}</#if> is at your selected Froom Location and the further Information as follow
    </b></div>
<br>
<div style="text-align: left">
    <b>Froom Location:</b>


</div>
<br/>

<br>

<br>
<div style="text-align:center">
    <button class="btn btn-primary">Track Your Order</button>
    <br> <br>
    <img src="qr_code.jpg" alt="QR Code / Check Attachments.">
</div>
<br>

<br><br>

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