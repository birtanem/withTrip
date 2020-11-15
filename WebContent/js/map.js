	$(document).ready(function() {
			
		$('#Buk-gu').mouseover(function() {
			$('#Buk').css('-webkit-animation','bounce-in-top 1.1s both')
			.css('animation','bounce-in-top 1.1s both');
			
// 				opacity: '1',
// 				transition: '0.5s'
			
		});
		$('#Buk-gu').mouseout(function() {
			$('#Buk').css('-webkit-animation','')
			.css('animation','')
			.css('opacity','1');
			
// 			$('#Buk').css({
				
// 				opacity: '0',
// 				transition: '0.5s'
// 			});
		});
		$('#Busanjin-gu').mouseover(function() {
			$('#Busanjin').css({
				opacity: '1',
				transition: '0.5s'
			});
		});
		$('#Busanjin-gu').mouseout(function() {
			$('#Busanjin').css({
				opacity: '0',
				transition: '0.5s'
			});
		});
		$('#Dong-gu').mouseover(function() {
			$('#Dong').css({
				opacity: '1',
				transition: '0.5s'
			});
		});
		$('#Dong-gu').mouseout(function() {
			$('#Dong').css({
				opacity: '0',
				transition: '0.5s'
			});
		});
		$('#Dongnae-gu').mouseover(function() {
			$('#Dongnae').css({
			opacity: '1',
			transition: '0.5s'
			});
		});
		$('#Dongnae-gu').mouseout(function() {
			$('#Dongnae').css({
				opacity: '0',
				transition: '0.5s'
			});
		});
		$('#Geumjeong-gu').mouseover(function() {
			$('#Geumjeong').css({
			opacity: '1',
			transition: '0.5s'
			});
		});
		$('#Geumjeong-gu').mouseout(function() {
			$('#Geumjeong').css({
			opacity: '0',
			transition: '0.5s'
			});
		});
		$('#Haeundae-gu').mouseover(function() {
			$('#Haeundae').css({
			opacity: '1',
			transition: '0.5s'
			});
		});
		$('#Haeundae-gu').mouseout(function() {
			$('#Haeundae').css({
			opacity: '0',
			transition: '0.5s'
			});
		});
		$('#Jung-gu').mouseover(function() {
			$('#Jung').css({
			opacity: '1',
			transition: '0.5s'
			});
		});
		$('#Jung-gu').mouseout(function() {
			$('#Jung').css({
			opacity: '0',
			transition: '0.5s'
			});
		});
		$('#Nam-gu').mouseover(function() {
			$('#Nam').css({
			opacity: '1',
			transition: '0.5s'
			});
		});
		$('#Nam-gu').mouseout(function() {
			$('#Nam').css({
			opacity: '0',
			transition: '0.5s'
			});
		});
		
					
		
		
			$('#Buk-gu').click(function() {
				$('#Buk-gu').css({
					
					transform: 'translate(-200px,-100px) scale(2)',
					transition: '3s'
			});
			$('#Buk').css({
					
				opacity: '1',
				transition: '3s'
					
				});
				$('#Busanjin-gu').css("opacity","0");
				$('#Dong-gu').css("opacity","0");
				$('#Dongnae-gu').css("opacity","0");
				$('#Gangseo-gu').css("opacity","0");
				$('#Geumjeong-gu').css("opacity","0");
				$('#Haeundae-gu').css("opacity","0");
				$('#Jung-gu').css("opacity","0");
				$('#Nam-gu').css("opacity","0");
				$('#Saha-gu').css("opacity","0");
				$('#Sasang-gu').css("opacity","0");
				$('#Seo-gu').css("opacity","0");
				$('#Suyeong-gu').css("opacity","0");
				$('#Yeongdo-gu').css("opacity","0");
				$('#Yeonje-gu').css("opacity","0");
				$('#Seo-gu').css("opacity","0");
				$('#Gijang-gun').css("opacity","0");
				
			});
			
			$('#Busanjin-gu').click(function() {
				$('#Busanjin-gu').css({
					
					transform: 'translate(-500px,-500px) scale(3)',
					transition: '3s'
				});
				$('#Busanjin').css({
					
					opacity: '1',
					transition: '3s'
					
				});
				$('#Buk-gu').css("opacity","0");
				$('#Dong-gu').css("opacity","0");
				$('#Dongnae-gu').css("opacity","0");
				$('#Gangseo-gu').css("opacity","0");
				$('#Geumjeong-gu').css("opacity","0");
				$('#Haeundae-gu').css("opacity","0");
				$('#Jung-gu').css("opacity","0");
				$('#Nam-gu').css("opacity","0");
				$('#Saha-gu').css("opacity","0");
				$('#Sasang-gu').css("opacity","0");
				$('#Seo-gu').css("opacity","0");
				$('#Suyeong-gu').css("opacity","0");
				$('#Yeongdo-gu').css("opacity","0");
				$('#Yeonje-gu').css("opacity","0");
				$('#Seo-gu').css("opacity","0");
				$('#Gijang-gun').css("opacity","0");
				
			});
			
			$('#Dong-gu').click(function() {
				$('#Dong-gu').css({
					
					transform: 'translate(-800px,-1000px) scale(4)',
					transition: '3s'
				});
				$('#Buk-gu').css("opacity","0");
				$('#Busanjin-gu').css("opacity","0");
				$('#Dongnae-gu').css("opacity","0");
				$('#Gangseo-gu').css("opacity","0");
				$('#Geumjeong-gu').css("opacity","0");
				$('#Haeundae-gu').css("opacity","0");
				$('#Jung-gu').css("opacity","0");
				$('#Nam-gu').css("opacity","0");
				$('#Saha-gu').css("opacity","0");
				$('#Sasang-gu').css("opacity","0");
				$('#Seo-gu').css("opacity","0");
				$('#Suyeong-gu').css("opacity","0");
				$('#Yeongdo-gu').css("opacity","0");
				$('#Yeonje-gu').css("opacity","0");
				$('#Seo-gu').css("opacity","0");
				$('#Gijang-gun').css("opacity","0");
				
			});
		});