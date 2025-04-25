<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<form action="/admin/product/add" method="post" enctype="multipart/form-data">
		<h1>${param.message }</h1>
		<div class="mb-3 mt-3">
		    <label for="name" class="form-label">Id:</label>
		    <input name="id" type="text" id="name" class="form-control" value="${user.id }"/>
		</div>
		<div class="mb-3 mt-3">
		    <label for="name" class="form-label">Name:</label>
		    <input name="name" type="text" id="name" class="form-control" value="${user.name }" required/>
		</div>
		<div class="mb-3 mt-3">
		    <label for="email" class="form-label">Image:</label>
		    <input type="number" class="form-control" id="email" placeholder="" name="price" value="${user.price }" required min="1">
		</div>
		<div class="mb-3 mt-3">
		    <label for="a" class="form-label">Price:</label>
		    <input type="file" class="form-control" id="a" placeholder="" name="file" required>
		</div>
		<select class="form-select" name="cate">
			<c:forEach items="${categories }" var="i">
		  		<option value="${i.id}" ${user.category.id == i.id ? "selected" : ""}>${i.name }</option>
			</c:forEach>
		</select>
		<button type="submit" class="btn btn-primary">Submit</button>
		<button formaction="/admin/product/update" class="btn btn-warning">Update</button>
		<button type="reset" class="btn btn-warning">Reset</button>
</form>
<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">name</th>
      <th scope="col">image</th>
      <th scope="col">price</th>
      <th scope="col">createDate</th>
      <th scope="col">category</th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${products }" var="a">
  		<tr>
	      <td>${a.id }</td>
	      <td>${a.name }</td>
	      <td><img src="/resources/img/${a.image }" width="40%"/></td>
	      <td>${a.price }</td>
	      <td>${a.createDate }</td>
	      <td>${a.category.name }</td>
	      <td><a href="/admin/product/edit/${a.id}">edit</a></td>
	      <td><a href="/admin/product/delete/${a.id}">delete</a></td>
	    </tr>
  	</c:forEach>
  </tbody>
</table>
</html>