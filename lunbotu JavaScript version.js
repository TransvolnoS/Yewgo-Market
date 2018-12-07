window.onload=function(){
	window.setInterval(alterpic,2000);
	window.setTimeout(showad,2000);
	
}
var num=1;
function alterpic(){
	num++;
	if(num==4){
		num=1;
		}
	var a=document.getElementById("pic001");
	a.src="images/"+num+".jpg";
}

var img;
function showad()
	{
		img=document.getElementById("ad001");
		img.style.display="inline";
		window.setTimeout(hiddenad,2000);
	}
	
function hiddenad()
	{
		img.style.display="none";
	}
	