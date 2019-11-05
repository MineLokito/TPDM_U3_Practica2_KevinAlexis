<?php
$con = mysqli_connect('us-cdbr-iron-east-05.cleardb.net',
				'b6d83b4c20b098',
				'822220d0',
				'heroku_7f44651a9a185e5'
);

if(!$con){
	echo "No se Conecto :c";
	return;

}
$SQL = "SELECT * FROM recibopago";
	$respuesta = mysqli_query($con,$SQL);

	$numRenglones = mysqli_num_rows($respuesta);
	if($numRenglones>0){
		while ($renglon = $respuesta->fetch_object()) {
			$data[] = $renglon;
		}
		echo (json_encode($data));
	}else{
		echo "no hay datos";
	}




?>