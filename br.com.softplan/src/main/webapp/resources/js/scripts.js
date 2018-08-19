
function scroll_to(clicked_link, nav_height) {
	var element_class = clicked_link.attr('href').replace('#', '.');
	var scroll_to = 0;
	if(element_class != '.top-content') {
		element_class += '-container';
		scroll_to = $(element_class).offset().top - nav_height;
	}
	if($(window).scrollTop() != scroll_to) {
		$('html, body').stop().animate({scrollTop: scroll_to}, 1000);
	}
}


jQuery(document).ready(function() {
	
	/*
	    Navigation
	*/
	$('a.scroll-link').on('click', function(e) {
		e.preventDefault();
		scroll_to($(this), $('nav').outerHeight());
	});
	// toggle "navbar-no-bg" class
	$('.top-content .text').waypoint(function() {
		$('nav').toggleClass('navbar-no-bg');
	});
	
    /*
        Background slideshow
    */
    $('.top-content').backstretch(
    		["resources/img/backgrounds/1.jpg", "resources/img/backgrounds/2.jpg", "resources/img/backgrounds/3.jpg"], 
    		{duration: 3000, fade: 750}
    	);
    $('.call-to-action-container').backstretch("resources/img/backgrounds/1.jpg");
    $('.testimonials-container').backstretch("resources/img/backgrounds/1.jpg");
    
    $('#top-navbar-1').on('shown.bs.collapse', function(){
    	$('.top-content').backstretch("resize");
    });
    $('#top-navbar-1').on('hidden.bs.collapse', function(){
    	$('.top-content').backstretch("resize");
    });
    
    $('a[data-toggle="tab"]').on('shown.bs.tab', function() {
    	$('.testimonials-container').backstretch("resize");
    });
    
    /*
        Wow
    */
    new WOW().init();
    
    /*
	    Subscription form
	*/
	$('.success-message, .error-message').hide();
	
	$('.subscribe form').submit(function(e) {
		e.preventDefault();
	    var postdata = $('.subscribe form').serialize();
	    $.ajax({
	        type: 'POST',
	        url: 'resources/subscribe.php',
	        data: postdata,
	        dataType: 'json',
	        success: function(json) {
	            if(json.valid == 0) {
	            	$('.success-message, .error-message').hide();
	                $('.error-message').html(json.message).fadeIn();
	            }
	            else {	                
	                $('.success-message, .error-message, .subscribe form').hide();
	                $('.success-message').html(json.message).fadeIn();
	            }
	        }
	    });
	});
	
	
});


jQuery(window).load(function() {
	
	/*
		Loader
	*/
	$(".loader-img").fadeOut();
	$(".loader").delay(1000).fadeOut("slow");
	
	/*
		Hidden images
	*/
	$(".testimonial-image img").attr("style", "width: auto !important; height: auto !important;");
	
});

