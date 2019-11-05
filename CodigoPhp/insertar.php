<?php
$con = mysqli_connect('us-cdbr-iron-east-05.cleardb.net',
				'b6d83b4c20b098',
				'822220d0',
				'heroku_7f44651a9a185e5'
);

//b6d83b4c20b098:822220d0@us-cdbr-iron-east-05.cleardb.net/heroku_7f44651a9a185e5?reconnect=true

if(!$con){
	echo "No se Conecto :c";
	return;

}
//recuperar mis variables enviadas desde el formulario
$descripcion = $_POST["descripcion"];
$monto = $_POST["monto"];
$fechavencimiento = $_POST["fechavencimiento"];
$pagado = $_POST["pagado"];


$SQL = "INSERT INTO recibopago (descripcion, monto ,fechavencimiento ,pagado) 
VALUES('$descripcion','$monto','$fechavencimiento','$pagado')";

$respuesta = mysqli_query($con, $SQL);
if($respuesta){
echo "Si Se Inserto";
}else{
echo "No Se Inserto";
}
?>