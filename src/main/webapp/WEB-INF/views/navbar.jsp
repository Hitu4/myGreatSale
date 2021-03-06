<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CUstom Css For the Nav bar -->



<spring:url value="/resources/core/css/Custom1.css" var="Custom1"></spring:url>
<link href="${Custom1}" rel="stylesheet">


</head>

<!---NAV BAR-->

<nav class="navbar navbar-inverse">
	<ul class="nav navbar-nav">

		<li class="active"><a href="<c:url value= '/'/>">Home</a></li>

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">Our Info<span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="#">Contact us</a></li>
				<li><a href="#">History</a></li>
			</ul></li>

		<li><a href="CategoryMens">Mens</a></li>

		<li class="dropdown mega-dropdown"><a href="#"
			class="dropdown-toggle" data-toggle="dropdown">View All By Category<span
				class="caret"></span></a>
				
			<ul class="dropdown-menu mega-dropdown-menu">
			<c:forEach items="${categoryList}" var="category">
			
				<li class="col-sm-3">
					<ul>
						<li class="dropdown-header">${category.name}</li>
						<li class="divider"></li>
						<c:forEach items="${category.products}" var="product">
							<li><a href="manage_product/get/${product.id}">${product.name}</a></li>
						</c:forEach>
					</ul>
				</li>
				
			</c:forEach>	
				
				<li class="col-sm-3">
					<ul>
						<li class="dropdown-header">Women Collection</li>
						<div id="womenCollection" class="carousel slide"
							data-ride="carousel">
							<div class="carousel-inner">
								<div class="item active">
									<a href="#"><img
										src="http://placehold.it/254x150/3498db/f5f5f5/&text=New+Collection"
										class="img-responsive" alt="product 1"></a>
									<h4>
										<small>Summer dress floral prints</small>
									</h4>
									<button class="btn btn-primary" type="button">49,99 €</button>
									<button href="#" class="btn btn-default" type="button">
										<span class="glyphicon glyphicon-heart"></span> Add to
										Wishlist
									</button>
								</div>
								<!-- End Item -->
								<div class="item">
									<a href="#"><img
										src="http://placehold.it/254x150/ff3546/f5f5f5/&text=New+Collection"
										class="img-responsive" alt="product 2"></a>
									<h4>
										<small>Gold sandals with shiny touch</small>
									</h4>
									<button class="btn btn-primary" type="button">9,99 €</button>
									<button href="#" class="btn btn-default" type="button">
										<span class="glyphicon glyphicon-heart"></span> Add to
										Wishlist
									</button>
								</div>
								<!-- End Item -->
								<div class="item">
									<a href="#"><img
										src="http://placehold.it/254x150/2ecc71/f5f5f5/&text=New+Collection"
										class="img-responsive" alt="product 3"></a>
									<h4>
										<small>Denin jacket stamped</small>
									</h4>
									<button class="btn btn-primary" type="button">49,99 €</button>
									<button href="#" class="btn btn-default" type="button">
										<span class="glyphicon glyphicon-heart"></span> Add to
										Wishlist
									</button>
								</div>
								<!-- End Item -->
							</div>
							<!-- End Carousel Inner -->

							<!-- Controls -->

							<a id="left carousel-control" href="#womenCollection"
								role="button" data-slide="prev"> <span
								id="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a> <a id="right carousel-control"
								href="
											#womenCollection" role="button"
								data-slide="next"> <span
								id="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
								<span class="sr-only">Next</span>
							</a>
						</div>
						<!-- /.carousel -->

						<li class="divider"></li>
						<li><a href="#">View all Collection <span
								class="glyphicon glyphicon-chevron-right pull-right"></span></a></li>
					</ul>
				</li>

			</ul>
		</li>
		
		<li  class="shopping" ><a href="<c:url value= 'myCart'/>">Shopping Cart<span class="glyphicon glyphicon-shopping-cart"></span></a></li>
		
	</ul>
	<!--  	<ul class="nav navbar-nav navbar-right">
						<li><a href="Signup"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li><a href="Login"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</ul> -->

</nav>


</body>
</html>