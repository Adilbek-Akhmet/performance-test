package performance.test.product.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import performance.test.product.dto.ProductDto;
import performance.test.product.services.ProductService;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    public List<ProductDto> findAll(@QueryParam("page") @DefaultValue("0") int page,
                                    @QueryParam("size") @DefaultValue("50") int size) {
        return productService.findAll(page, size);
    }

    @GET
    @Path("/{id}")
    public ProductDto findById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @POST
    public Response save(ProductDto productDto) {
        ProductDto savedProduct = productService.save(productDto);
        return Response.status(Response.Status.CREATED).entity(savedProduct).build();
    }
}

