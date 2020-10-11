
$(function() { 
	
	 $("#customer-form").validate({
		 rules: { 
			 email : {
				email : true  ,
	            required : true 
			 } ,
			 firstname : "required" , 
			 lastname : "required" , 
			 password  : "required" , 
			 confPassword : { 
				 required : true  , 
				 equalTo : "#password"
			 },
			 phoneNumber : "required" , 
			 address1 : "required" ,  
			 address2 : "required" , 
			 city : "required" , 
			 zipCode : "required" , 
			 country : "required" , 
			 state : "required"
			 
		 } , 
		 messages: { 
			 email : {
				 email : "Please enter a valid e-mail" , 
				 required : "Please enter e-mail address"
			 }, 
			 firstname :"Please enter first name." ,
			 lastname : "Plase enter last name"  , 
			 password : "Please enter password.",
			 confPassword : {
				 required : "Please confirm  password." , 
				 equalTo : "Wrong password"
			 } , 
			 phoneNumber : "Please enter phone number." , 
			 address1 : "Please enter address line 1." , 
			 address2 : "Please enter address line 2" , 
			 state : "Please enter state" , 
			 city : "Please enter city." , 
			 zipCode : "Please enter zip code." , 
			 country : "Please enter country." 
		 }
	 }) ; 
}) ; 

  