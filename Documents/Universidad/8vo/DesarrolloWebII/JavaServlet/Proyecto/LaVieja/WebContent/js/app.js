//alert("hola");
var div = [];
var estado = [], estado2 = [], est = [];
var j1, j2; //jugadores
var p1 = 0, p2 = 0; //puntajes
var id;
var open = 0;
var flag1;
var flag2;
var ws = new WebSocket("ws://localhost:8080/LaVieja/server");

ws.onopen = function(e){
	open = 1;
	console.log(open);
    flag1 = true;
    flag2 = false;
};

for(var i = 0; i < 9; i++){
	estado[i] = 0;
	estado2[i] = 0;
	est[i] = 0;
	console.log("estado original: " + estado[i]);
	div[i] = obElem(i);
	div[i].innerText = " ";
}

j1 = obElem('j1');
j2 = obElem('j2');

j1.innerText = p1;
j2.innerText = p2;

function iniciar(){
	if(open == 1){
		obElem('campo').style.display = "block";
		obElem('j1').style.display = "block";
		obElem('j2').style.display = "block";
		obElem('reinicio').style.display = "block";
	}
}

function obElem(id){
    return document.getElementById(id);
}

function crearElem(id){
    return document.createElement(id);
}

function marcar(event){
	id= parseInt(event.target.id);
	var json = {
			"id" : id
	}
    
    if(estado[id] == 0 && flag1 == true){
    	//div[id] = obElem(id);
    	ws.send(id);
        div[id].innerText = "X";
        estado[id] = 1;
        est [id] = 1;
        console.log(estado[id]);
        flag1 = false;
        flag2 = true;
        verificar();
    }
}

ws.onmessage = function (e) {
	console.log(e.data);
	if(estado[e.data] == 0){
		div[e.data].innerText = "O";
		estado2[e.data] = 1;
		est [e.data] = 1;
		flag2 = false;
        flag1 = true;
		verificar();
	}
};

function color(a, b, c){
	obElem(a).style.color="rgba(255,0,0,1)";
	obElem(b).style.color="rgba(255,0,0,1)";
	obElem(c).style.color="rgba(255,0,0,1)";
}

function verificar(){
	console.log("J1= " + estado);
	console.log("J2= " + estado2);
	console.log("Gene= " + est);
	
	if(estado[0] == 1 && estado[4] == 1 && estado[8] == 1){
		//alert("¡Eureka1! 0-4-8");
		color(0, 4, 8);
		p1 ++;
		j1.innerText = p1;
		borrarTablero();
	}else if(estado[2] == 1 && estado[4] == 1 && estado[6] == 1){
		//alert("¡Eureka1! 2-4-6");
		color(2, 4, 6);
		p1 ++;
		j1.innerText = p1;
		borrarTablero();
	}else if(estado[0] == 1 && estado[3] == 1 && estado[6] == 1){
		//alert("¡Eureka1! 0-3-6");
		color(0, 3, 6);
		p1 ++;
		j1.innerText = p1;
		borrarTablero();
	}else if(estado[1] == 1 && estado[4] == 1 && estado[7] == 1){
		//alert("¡Eureka1! 1-4-7");
		color(1, 4, 7);
		p1 ++;
		j1.innerText = p1;
		borrarTablero();
	}else if(estado[2] == 1 && estado[5] == 1 && estado[8] == 1){
		//alert("¡Eureka1! 2-5-8");
		color(2, 5, 8);
		p1 ++;
		j1.innerText = p1;
		borrarTablero();
	}else if(estado[0] == 1 && estado[1] == 1 && estado[2] == 1){
		//alert("¡Eureka1! 0-1-2");
		color(0, 1, 2);
		p1 ++;
		j1.innerText = p1;
		borrarTablero();
	}else if(estado[3] == 1 && estado[4] == 1 && estado[5] == 1){
		//alert("¡Eureka1! 3-4-5");
		color(3, 4, 5);
		p1 ++;
		j1.innerText = p1;
		borrarTablero();
	}else if(estado[6] == 1 && estado[7] == 1 && estado[8] == 1){
		//alert("¡Eureka1! 6-7-8");
		color(6, 7, 8);
		p1 ++;
		j1.innerText = p1;
		borrarTablero();
	}else if(estado2[0] == 1 && estado2[4] == 1 && estado2[8] == 1){
		//alert("¡Eureka2! 0-4-8");
		color(0, 4, 8);
		p2 ++;
		j2.innerText = p2;
		borrarTablero();
	}else if(estado2[2] == 1 && estado2[4] == 1 && estado2[6] == 1){
		//alert("¡Eureka2! 2-4-6");
		color(2, 4, 6);
		p2 ++;
		j2.innerText = p2;
		borrarTablero();
	}else if(estado2[0] == 1 && estado2[3] == 1 && estado2[6] == 1){
		//alert("¡Eureka2! 0-3-6");
		color(0, 3, 6);
		p2 ++;
		j2.innerText = p2;
		borrarTablero();
	}else if(estado2[1] == 1 && estado2[4] == 1 && estado2[7] == 1){
		//alert("¡Eureka2! 1-4-7");
		color(1, 4, 7);
		p2 ++;
		j2.innerText = p2;
		borrarTablero();
	}else if(estado2[2] == 1 && estado2[5] == 1 && estado2[8] == 1){
		//alert("¡Eureka2! 2-5-8");
		color(2, 5, 8);
		p2 ++;
		j2.innerText = p2;
		borrarTablero();
	}else if(estado2[0] == 1 && estado2[1] == 1 && estado2[2] == 1){
		//alert("¡Eureka2! 0-1-2");
		color(0, 1, 2);
		p2 ++;
		j2.innerText = p2;
		borrarTablero();
	}else if(estado2[3] == 1 && estado2[4] == 1 && estado2[5] == 1){
		//alert("¡Eureka2! 3-4-5");
		color(3, 4, 5);
		p2 ++;
		j2.innerText = p2;
		borrarTablero();
	}else if(estado2[6] == 1 && estado2[7] == 1 && estado2[8] == 1){
		//alert("¡Eureka2! 6-7-8");
		color(6, 7, 8);
		p2 ++;
		j2.innerText = p2;
		borrarTablero();
	}else if(est[1] == 1 && est[2] == 1 && est[3] == 1 && est[4] == 1 && est[5] == 1 && est[6] == 1 && est[7] == 1 && est[8] == 1 && est[0] == 1){
		//alert("¡Eureka! EMPATE");
		color(0, 1, 2);
		color(3, 4, 5);
		color(6, 7, 8);
		borrarTablero();
	}
	
	if(p1 == 3 || p2 == 3){
		flag1=true;
		flag2=false;
		reiniciar();
	}
}

function borrarTablero(){
	setTimeout(function(){
		for(var i = 0; i < 9; i++){
			estado[i] = 0;
			estado2[i] = 0;
			est[i] = 0;
			div[i].innerText =" ";
		} 
	}, 1000);
}

function reiniciar(){
	location.reload();
}

//window.onunload = ()=>{
//    ws.onclose();
//}