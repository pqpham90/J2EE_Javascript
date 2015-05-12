/**
 * 
 */

function loadXMLDoc(url,cfunc)
{
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=cfunc;
    xmlhttp.open("GET",url,false);
    xmlhttp.send();
}
function myFunction()
{
    loadXMLDoc("http://localhost:8080/assignment7/myeavesdrop",function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            xmlDoc=xmlhttp.responseXML;

            document.getElementById("meetingData").innerHTML = "";

            var table = document.createElement('table');
            var x=xmlDoc.getElementsByTagName("meeting");

            var tr = document.createElement('tr');

            var td1 = document.createElement('td');
            var td2 = document.createElement('td');

            var text1 = document.createTextNode("Project");
            var text2 = document.createTextNode("Count");

            td1.appendChild(text1);
            td2.appendChild(text2);
            tr.appendChild(td1);
            tr.appendChild(td2);

            table.appendChild(tr);

            for (var i=0;i<x.length;i++)
            {
                tr = document.createElement('tr');

                td1 = document.createElement('td');
                td2 = document.createElement('td');

                text1 = document.createTextNode(x[i].getElementsByTagName("name")[0].childNodes[0].nodeValue);
                text2 = document.createTextNode(x[i].getElementsByTagName("count")[0].childNodes[0].nodeValue);

                td1.appendChild(text1);
                td2.appendChild(text2);
                tr.appendChild(td1);
                tr.appendChild(td2);

                table.appendChild(tr);
            }
            document.getElementById("meetingData").appendChild(table);
        }
    });
}