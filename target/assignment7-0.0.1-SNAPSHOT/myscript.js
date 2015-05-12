function loadXMLDoc()
{
    var url = "http://localhost:8080/mwa/assignment7/myeavesdrop";

    var xmlhttp;
    var txt,x,xx,i;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            txt="<table class=\"table table-hover\"><thead><tr><th>Project</th><th>Count</th></tr></thead><tbody>";
            x=xmlhttp.responseXML.documentElement.getElementsByTagName("meeting");
            for (i=0;i<x.length;i++)
            {
                txt=txt + "<tr>";
                xx=x[i].getElementsByTagName("name");
                {
                    try
                    {
                        txt=txt + "<td>" + xx[0].firstChild.nodeValue + "</td>";
                    }
                    catch (er)
                    {
                        txt=txt + "<td> </td>";
                    }
                }
                xx=x[i].getElementsByTagName("count");
                {
                    try
                    {
                        txt=txt + "<td>" + xx[0].firstChild.nodeValue + "</td>";
                    }
                    catch (er)
                    {
                        txt=txt + "<td> </td>";
                    }
                }
                txt=txt + "</tr>";
            }
            txt=txt + "</tbody></table>";
            document.getElementById('meetingData').innerHTML=txt;
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}