
function musicadd(){
	var id=document.querySelector('input[name=id]').value;
	var url="addmusic.jsp?id="+id;
	window.open(url,"_blank",
	"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=300");
}



function Ok(){
	opener.location.reload();
	self.close();
}

