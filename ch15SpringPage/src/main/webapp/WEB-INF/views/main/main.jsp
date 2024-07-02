<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 메인 시작 -->
<div class="page-main">
	<h3>메인</h3>
	
	<script>
    (function(){var w=window;if(w.ChannelIO){return w.console.error("ChannelIO script included twice.");}var ch=function(){ch.c(arguments);};ch.q=[];ch.c=function(args){ch.q.push(args);};w.ChannelIO=ch;function l(){if(w.ChannelIOInitialized){return;}w.ChannelIOInitialized=true;var s=document.createElement("script");s.type="text/javascript";s.async=true;s.src="https://cdn.channel.io/plugin/ch-plugin-web.js";var x=document.getElementsByTagName("script")[0];if(x.parentNode){x.parentNode.insertBefore(s,x);}}if(document.readyState==="complete"){l();}else{w.addEventListener("DOMContentLoaded",l);w.addEventListener("load",l);}})();
  
    ChannelIO('boot', {
      "pluginKey": "205221c2-22f9-4daa-a095-481970600126",
      "memberId": "${user.id}", // fill user's member id
      "profile": { // fill user's profile
        "name": "${user.id}", // fill user's name
        "mobileNumber": "01033334444", // fill user's mobile number
        "landlineNumber": "USER_LANDLINE_NUMBER", // fill user's landline number  
        "CUSTOM_VALUE_1": "VALUE_1", // custom property
        "CUSTOM_VALUE_2": "VALUE_2" // custom property
      }
    });
  </script>
</div>
<!-- 메인 끝 -->