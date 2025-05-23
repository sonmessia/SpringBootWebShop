<%@page import="java.net.URL"%>
<%@page import="org.springframework.data.domain.Page"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>EShopper - Bootstrap Shop Template</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="/resources/img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/resources/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="/resources/css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Hiển thị thông báo nếu có -->
	<c:if test="${not empty message}">
		<div class="alert alert-success">${message}</div>
	</c:if>
	<!-- Topbar Start -->
	<div class="container-fluid">
		<div class="row bg-secondary py-2 px-xl-5">
			<div class="col-lg-6 d-none d-lg-block">
				<div class="d-inline-flex align-items-center">
					<a class="text-dark" href="">FAQs</a> <span class="text-muted px-2">|</span>
					<a class="text-dark" href="">Help</a> <span class="text-muted px-2">|</span>
					<a class="text-dark" href="">Support</a>
				</div>
			</div>
			<div class="col-lg-6 text-center text-lg-right">
				<div class="d-inline-flex align-items-center">
					<a class="text-dark px-2" href=""> <i class="fab fa-facebook-f"></i>
					</a> <a class="text-dark px-2" href=""> <i class="fab fa-twitter"></i>
					</a> <a class="text-dark px-2" href=""> <i
						class="fab fa-linkedin-in"></i>
					</a> <a class="text-dark px-2" href=""> <i class="fab fa-instagram"></i>
					</a> <a class="text-dark pl-2" href=""> <i class="fab fa-youtube"></i>
					</a>
				</div>
			</div>
		</div>
		<div class="row align-items-center py-3 px-xl-5">
			<div class="col-lg-3 d-none d-lg-block">
				<a href="/" class="text-decoration-none">
					<h1 class="m-0 display-5 font-weight-semi-bold">
						<span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper
					</h1>
				</a>
			</div>
			<div class="col-lg-6 col-6 text-left">
				<form action="">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for products">
						<div class="input-group-append">
							<span class="input-group-text bg-transparent text-primary">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
				</form>
			</div>
			<div class="col-lg-3 col-6 text-right">
				<a href="/cart/view" class="btn border"> <i
					class="fas fa-shopping-cart text-primary"></i> <span class="badge">${cartNumber }</span>
				</a>
			</div>
		</div>
	</div>
	<!-- Topbar End -->


	<!-- Navbar Start -->
	<div class="container-fluid">
		<div class="row border-top px-xl-5">
			<div class="col-lg-3 d-none d-lg-block">
				<a
					class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100"
					data-toggle="collapse" href="#navbar-vertical"
					style="height: 65px; margin-top: -1px; padding: 0 30px;">
					<h6 class="m-0">Categories</h6> <i
					class="fa fa-angle-down text-dark"></i>
				</a>
				<nav
					class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light"
					id="navbar-vertical" style="width: calc(100% - 30px); z-index: 1;">
					<div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
						<a href="/shop/0" class="nav-item nav-link">All category</a>
						<c:forEach var="item" items="${cs }">
							<a href="/shop/0?category=${item.id}" class="nav-item nav-link">${item.name }</a>
						</c:forEach>
					</div>
				</nav>
			</div>
			<div class="col-lg-9">
				<nav
					class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
					<a href="/" class="text-decoration-none d-block d-lg-none">
						<h1 class="m-0 display-5 font-weight-semi-bold">
							<span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper
						</h1>
					</a>
					<button type="button" class="navbar-toggler" data-toggle="collapse"
						data-target="#navbarCollapse">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-between"
						id="navbarCollapse">
						<div class="navbar-nav mr-auto py-0">
							<a href="/" class="nav-item nav-link">Home</a> <a href="/shop/0"
								class="nav-item nav-link active">Shop</a>
						</div>
													<%= request.getAttribute("user") != null ? "<div class='navbar-nav ml-auto py-0'><a href='/logout' class='nav-item nav-link'>Logout</a></div>" : "<div class='navbar-nav ml-auto py-0'><a href='/login' class='nav-item nav-link'>Login</a> <a href='/registrator' class='nav-item nav-link'>Register</a></div>"%>

					</div>
				</nav>
			</div>
		</div>
	</div>
	<!-- Navbar End -->


	<!-- Page Header Start -->
	<div class="container-fluid bg-secondary mb-5">
		<div
			class="d-flex flex-column align-items-center justify-content-center"
			style="min-height: 300px">
			<h1 class="font-weight-semi-bold text-uppercase mb-3">Our Shop</h1>
			<div class="d-inline-flex">
				<p class="m-0">
					<a href="/">Home</a>
				</p>
				<p class="m-0 px-2">-</p>
				<p class="m-0">Shop</p>
			</div>
		</div>
	</div>
	<!-- Page Header End -->


	<!-- Shop Start -->
	<div class="container-fluid pt-5">
		<div class="row px-xl-5">
			<!-- Shop Sidebar Start -->
			<div class="col-lg-3 col-md-12">
				<!-- Price Start -->
				<div class="border-bottom mb-4 pb-4">
					<h5 class="font-weight-semi-bold mb-4">Filter by price</h5>
					<a
						href="/shop/0?<%=request.getAttribute("ct") == null ? "" : "category=" + request.getAttribute("ct")%>&sort=true&price=0,1000">
						<div
							class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
							<input type="radio" class="custom-control-input" checked
								id="price-all" name="price"> <label
								class="custom-control-label" for="price-all">All Price</label> <span
								class="badge border font-weight-normal">1000</span>
						</div>
					</a> <a
						href="/shop/0?<%=request.getAttribute("ct") == null ? "" : "category=" + request.getAttribute("ct")%>&sort=true&price=0,100">
						<div
							class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
							<input type="radio" class="custom-control-input" id="price-1"
								name="price"> <label class="custom-control-label"
								for="price-1">$0 - $100</label> <span
								class="badge border font-weight-normal">150</span>
						</div>
					</a> <a
						href="/shop/0?<%=request.getAttribute("ct") == null ? "" : "category=" + request.getAttribute("ct")%>&sort=true&price=100,200">
						<div
							class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
							<input type="radio" class="custom-control-input" id="price-2"
								name="price"> <label class="custom-control-label"
								for="price-2">$100 - $200</label> <span
								class="badge border font-weight-normal">295</span>
						</div>
					</a> <a
						href="/shop/0?<%=request.getAttribute("ct") == null ? "" : "category=" + request.getAttribute("ct")%>&sort=true&price=200,300">
						<div
							class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
							<input type="radio" class="custom-control-input" id="price-3"
								name="price"> <label class="custom-control-label"
								for="price-3">$200 - $300</label> <span
								class="badge border font-weight-normal">246</span>
						</div>
					</a> <a
						href="/shop/0?<%=request.getAttribute("ct") == null ? "" : "category=" + request.getAttribute("ct")%>&sort=true&price=300,400">
						<div
							class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
							<input type="radio" class="custom-control-input" id="price-4"
								name="price"> <label class="custom-control-label"
								for="price-4">$300 - $400</label> <span
								class="badge border font-weight-normal">145</span>
						</div>
					</a>
				</div>
				<!-- Price End -->
			</div>
			<!-- Shop Sidebar End -->


			<!-- Shop Product Start -->
			<div class="col-lg-9 col-md-12">
				<div class="row pb-3">
					<div class="col-12 pb-1">
						<div
							class="d-flex align-items-center justify-content-between mb-4">
							<div class="dropdown ml-4">
								<button class="btn border dropdown-toggle" type="button"
									id="triggerId" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">Sort by</button>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="triggerId">
									<a class="dropdown-item" href="#">Decrease</a> <a
										class="dropdown-item" href="#">Increase</a>
								</div>
							</div>
						</div>
					</div>
					<c:forEach var="item" items="${ps.content }">
						<div class="col-lg-4 col-md-6 col-sm-12 pb-1">
							<div class="card product-item border-0 mb-4">
								<div
									class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
									<img class="img-fluid w-100"
										src="/resources/img/${item.image}" alt=""
										style="max-height: 250px; object-fit: contain;">
								</div>
								<div
									class="card-body border-left border-right text-center p-0 pt-4 pb-3">
									<h6 class="text-truncate mb-3">${item.name }</h6>
									<div class="d-flex justify-content-center">
										<h6>${item.price }VND</h6>
									</div>
								</div>
								<div
									class="card-footer d-flex justify-content-between bg-light border">
									<a href="/detail/${item.id }" class="btn btn-sm text-dark p-0"><i
										class="fas fa-eye text-primary mr-1"></i>View Detail</a> <a
										href="/cart/add/${item.id }?quantity=1&url=shop/${actionNumber}<%= request.getAttribute("ct") == null ? "" : "?category="+request.getAttribute("ct")%>" class="btn btn-sm text-dark p-0"><i
										class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="col-12 pb-1">
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center mb-3">
								<li class="page-item "><a class="page-link"
									href="/shop/${0}?<%= request.getAttribute("ct") == null ? "" : "category="+request.getAttribute("ct")%><%=request.getAttribute("price") == null ? "" :  "&price="+request.getAttribute("price")%>&sort=true"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
								</a></li>
								<%
								int a = Integer.valueOf(request.getAttribute("actionNumber").toString());
								int i = a - 1 < 0 ? 0 : a - 1;
								int max = a + 1 >= ((Page) request.getAttribute("ps")).getTotalPages() ? a : a + 1;
								while (i <= max) {
								%>
								<li class="page-item <%=i == a ? "active" : ""%>"><a
									class="page-link"
									href="/shop/<%=i%>?<%=request.getAttribute("ct") == null ? "" : "category=" + request.getAttribute("ct")%><%=request.getAttribute("price") == null ? "" :  "&price="+request.getAttribute("price")%>&sort=true"><%=i + 1%></a></li>
								<%
								i++;
								}
								%>
								<li class="page-item"><a class="page-link"
									href="/shop/${ps.totalPages - 1 }?<%= request.getAttribute("ct") == null ? "" : "category="+request.getAttribute("ct")%><%=request.getAttribute("price") == null ? "" :  "&price="+request.getAttribute("price")%>&sort=true"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<!-- Shop Product End -->
		</div>
	</div>
	<!-- Shop End -->


	<!-- Footer Start -->
	<div class="container-fluid bg-secondary text-dark mt-5 pt-5">
		<div class="row px-xl-5 pt-5">
			<div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
				<a href="" class="text-decoration-none">
					<h1 class="mb-4 display-5 font-weight-semi-bold">
						<span
							class="text-primary font-weight-bold border border-white px-3 mr-1">E</span>Shopper
					</h1>
				</a>
				<p>Dolore erat dolor sit lorem vero amet. Sed sit lorem magna,
					ipsum no sit erat lorem et magna ipsum dolore amet erat.</p>
				<p class="mb-2">
					<i class="fa fa-map-marker-alt text-primary mr-3"></i>123 Street,
					New York, USA
				</p>
				<p class="mb-2">
					<i class="fa fa-envelope text-primary mr-3"></i>info@example.com
				</p>
				<p class="mb-0">
					<i class="fa fa-phone-alt text-primary mr-3"></i>+012 345 67890
				</p>
			</div>
			<div class="col-lg-8 col-md-12">
				<div class="row">
					<div class="col-md-4 mb-5">
						<h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
						<div class="d-flex flex-column justify-content-start">
							<a class="text-dark mb-2" href="index.html"><i
								class="fa fa-angle-right mr-2"></i>Home</a> <a
								class="text-dark mb-2" href="shop.html"><i
								class="fa fa-angle-right mr-2"></i>Our Shop</a>
						</div>
					</div>
					<div class="col-md-4 mb-5">
						<h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
						<div class="d-flex flex-column justify-content-start">
							<a class="text-dark mb-2" href="index.html"><i
								class="fa fa-angle-right mr-2"></i>Home</a> <a
								class="text-dark mb-2" href="shop.html"><i
								class="fa fa-angle-right mr-2"></i>Our Shop</a>
						</div>
					</div>
					<div class="col-md-4 mb-5">
						<h5 class="font-weight-bold text-dark mb-4">Newsletter</h5>
						<form action="">
							<div class="form-group">
								<input type="text" class="form-control border-0 py-4"
									placeholder="Your Name" required="required" />
							</div>
							<div class="form-group">
								<input type="email" class="form-control border-0 py-4"
									placeholder="Your Email" required="required" />
							</div>
							<div>
								<button class="btn btn-primary btn-block border-0 py-3"
									type="submit">Subscribe Now</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row border-top border-light mx-xl-5 py-4">
			<div class="col-md-6 px-xl-0">
				<p class="mb-md-0 text-center text-md-left text-dark">
					&copy; <a class="text-dark font-weight-semi-bold" href="#">Your
						Site Name</a>. All Rights Reserved. Designed by <a
						class="text-dark font-weight-semi-bold"
						href="https://htmlcodex.com">HTML Codex</a>
				</p>
			</div>
			<div class="col-md-6 px-xl-0 text-center text-md-right">
				<img class="img-fluid" src="/resources/img/payments.png" alt="">
			</div>
		</div>
	</div>
	<!-- Footer End -->


	<!-- Back to Top -->
	<a href="#" class="btn btn-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/lib/easing/easing.min.js"></script>
	<script src="/resources/lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Contact Javascript File -->
	<script src="/resources/mail/jqBootstrapValidation.min.js"></script>
	<script src="/resources/mail/contact.js"></script>

	<!-- Template Javascript -->
	<script src="/resources/js/main.js"></script>
</body>

</html>