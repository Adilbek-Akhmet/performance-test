package performance.test.product.resources;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestQuery;
import performance.test.product.dto.ProductDto;
import performance.test.product.mappers.ProductMapper;
import performance.test.product.services.ProductService;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    public Uni<List<ProductDto>> findAll(@RestQuery @DefaultValue("0") int page,
                                         @RestQuery @DefaultValue("50") int size) {
        return productService.findAll(page, size);
    }

    @GET
    @Path("/{id}")
    public Uni<ProductDto> findById(@PathParam("id") Long id) {
        return productService.findById(id);
    }

    @POST
    public Uni<Response> save(ProductDto productDto) {
        return productService.save(ProductMapper.toEntity(productDto))
                .onItem().transform(savedProduct -> Response.status(Response.Status.CREATED).entity(savedProduct).build());
    }
}