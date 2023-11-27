package performance.test.product.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import performance.test.product.dto.ProductDto;
import performance.test.product.services.ProductService;

import java.util.List;
import java.util.Random;

@Path("/products/random")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRandomResource {

    @Inject
    ProductService productService;
    private static final Random random = new Random();


    @GET
    public List<ProductDto> findAll(@QueryParam("page") @DefaultValue("0") int page,
                                    @QueryParam("size") @DefaultValue("50") int size) {
        int newPage = random.nextInt(0, 500);
        return productService.findAll(newPage, size);
    }

    @GET
    @Path("/{id}")
    public ProductDto findById(@PathParam("id") Long id) {
        int newId = random.nextInt(0, 499_999);
        return productService.findById((long) newId);
    }

}
