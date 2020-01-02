package hubert.shop.Web;

import hubert.shop.data.ProductRepository;
import hubert.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProductByIdConverter implements Converter<UUID, Product> {

    private ProductRepository productRepository;

    @Autowired
    public ProductByIdConverter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product convert(UUID uuid) {
        Optional<Product> optionalProduct = productRepository.findById(uuid);
        return optionalProduct.orElse(null);
    }
}
