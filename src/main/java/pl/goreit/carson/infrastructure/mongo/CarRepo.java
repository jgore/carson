package pl.goreit.carson.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.goreit.carson.domain.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends MongoRepository<Car, String> {

    List<Car> findByOwner(String owner);
    Optional<Car> findByNo(String No);

}
