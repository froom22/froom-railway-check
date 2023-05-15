<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="content-type" content="text/html; char-set: UTF-8">
    <title>Froom Order Confirmed</title>
</head>
<body>
<div><b>Dear
        <#if name??>${name} </#if> <#if userName??> ${username}</#if>,</b> <br>
</div>

<div style="margin-top: 10px; text-align:center"><b>Thank you for choosing Froom!
    </b></div>
<br>
<div><b>Froom Order ID</b> <br>
    <#if froomOrderID??>${froomOrderID}</#if>
</div>
<br/>
<div> Your order is on its way to your chosen Froom location. <br>
    We will send an update once it is ready for pickup.
</div>
<br>

<br>
<div style="text-align:center">
    <button class="btn btn-primary">Track Your Order</button>
    <br> <br>
    <img src="qr_code.jpg" alt="QR Code / Check Attachments.">
</div>
<br>

<br><br>
<div style="text-align: left">
    <b>Froom Location:</b>
    <br>
    Rizaei's Tailor Shop<br>
    8 N Cass Ave, Westmont, IL 60559, United States<br>
    https://goo.gl/maps/2CLatfuuHaW2eLqv8<br>

</div>
<br>
<div style="text-align: left">
    <b>Pick-up Timings:</b><br>
    12 PM to 6 PM (Monday – Saturday)

</div>

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