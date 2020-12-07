package pl.goreit.carson.domain.service;

import pl.goreit.carson.api.DomainException;

import java.util.List;

public interface CrudService<Model, CreateRequest, ResponseModel> {

    ResponseModel create(CreateRequest createRequest);
    List<ResponseModel> findAll();
    ResponseModel save(Model model);
    void delete(String id);

}
