var xhr = new XMLHttpRequest();

function $(id){
	return document.getElementById(id);
}

function subir(){
	var formData = new FormData();
	formData.append("file", $("files").files[0]);
	myFile = $("files").files[0].name;
	console.log(myFile);
	
	xhr.onreadystatechange = function () {
		if (xhr.status === 200 && xhr.readyState === 4) {
			var data = JSON.parse(xhr.responseText);
			if(data.status == 200){
				$('TextArea').innerText = "Carga del Archivo Exitosa" + "\nPuede encontrar su archivo desencriptado acá\n" + data.Archivo + "\nFirma digital " + data.Firma;
				
			}else{
				$('TextArea').innerText = "Error al Cargar el archivo";
			}
		}
	}
	
	xhr.open("post", "./Subida", true);	
	xhr.send(formData);
	
	
}