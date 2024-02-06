

let getProductData=(data)=>{
	let xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(data){
		if(this.readyState==4 && this.status==200)
		{
			var option="<option>select an Product Category</option>";
				var datavalue=JSON.parse(this.responseText);
				for(var i in datavalue){
					option += "<option value = '" + datavalue[i][1] + "'>" + datavalue[i][0] +  "</option>";	
					console.log(option);
				}
			document.getElementById("productdropdown").innerHTML=option;			
		}
	}
	xhttp.open("GET","/products/"+data,true);
	xhttp.send();
}
