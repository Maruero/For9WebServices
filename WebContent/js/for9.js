/** ===============================================================
 * Função para validar valores obrigatórios
 ==================================================================**/

function validate(element){
	var response = true;
	$("[requiredLength]", element).each(function (element){
		var qtd = $(this).attr("requiredLength");
		if( $(this).val().length < qtd){
			if( response ){
				alert( $(this).attr("requiredMessage") );
				response = false;
			}
		}else if( $(this).val().length == 1 && new Number($(this).val()) < 1){
			alert( $(this).attr("requiredMessage") );
			response = false;
		}
	});
	return response;
}

var imageCount = 0;
setImageId = function(imagePath, imageId){
	$("img[imagem-index="+currentImagemIndex+"]").attr('src', imagePath);
	$("input[imagem-index="+currentImagemIndex+"]").val(imageId);
	$("button[imagem-index="+currentImagemIndex+"]").attr('data-imagemId', imageId);
};

var currentImagemIndex = -1;
function getCurrentImagemIndex(element){
	currentImagemIndex = $("#imagem-index", element).attr('imagem-index');
}

function removeImage(element){
	var imagemId = $(element).attr('data-imagemId');
	currentImagemIndex = $(element).attr('imagem-index');
	
	$.ajax({
		
		url : 'remover-imagem',
		type: 'POST',
		data: {
			'imagemId' : imagemId
		},
		success : function(data){
			$("img[imagem-index="+currentImagemIndex+"]").removeAttr('src');
			$("input[imagem-index="+currentImagemIndex+"]").val('');
		}
	});
	
}

function addImagemElements(){
	imageCount = $(".div-imagem").length;
	$("#addImagemElement-div").append(
		'<div class="div-imagem" imagem-index="'+imageCount+'">'+
			'<form class="form-imagem" action="carregar-imagem" target="frame-imagem" method="post" enctype="multipart/form-data" onsubmit="return getCurrentImagemIndex(this);">'+
				'<input type="file" name="image" />'+
				'<input type="submit" value="carregar" />'+
				'<input type="hidden" id="imagem-index" imagem-index="'+imageCount+'"/>'+
			'</form>'+
			'<img class="img-imagem" imagem-index="'+imageCount+'"/>'+
			'<button onclick="return removeImageDiv(this);" imagem-index="'+imageCount+'">Excluir</button>'+
		'</div>'
	);
	
	$("#form-add-associado").append(
		'<input type="hidden" imagem-index="'+imageCount+'" name="associado.listImagem['+imageCount+'].id" value="" requiredLength="1" requiredMessage="Por favor, carregue a imagem ou clique em excluir imagem." />'	
	);
	
	$("#form-add-agenda").append(
		'<input type="hidden" imagem-index="'+imageCount+'" name="agenda.listImagem['+imageCount+'].id" value="" requiredLength="1" requiredMessage="Por favor, carregue a imagem ou clique em excluir imagem." />'	
	);
}

function removeImageDiv(element){
	var index = $(element).attr('imagem-index');
	var parent = $(element).parent();
	$(parent).remove();
	
	$("input[imagem-index="+index+"]").remove();
	removeImage(element);
}