<?php
	function get_ip_place()     
	{     
	  
	    $ip=file_get_contents("http://fw.qq.com/ipaddress");     
	  
	    $ip=str_replace('"',' ',$ip);     
	  
	    $ip2=explode("(",$ip);     
	  
	    $a=substr($ip2[1],0,-2);     
	  
	    $b=explode(",",$a);     
	  
	    return $b;     
	  
	}   
?>