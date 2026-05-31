package lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lab.data.ComplaintRepository;
import lab.dto.ComplaintDTO;
import lab.entities.Complaint;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.util.List;
import java.lang.reflect.Type;

@ApplicationScoped
@Transactional
public class ComplaintService {

    @Inject
    private ComplaintRepository repository;

    @Inject
    private ModelMapper mapper;

    public void create(ComplaintDTO dto) {
        repository.create(mapper.map(dto, Complaint.class));
    }

    public void edit(ComplaintDTO dto) {
        repository.edit(mapper.map(dto, Complaint.class));
    }

    public void remove(ComplaintDTO dto) {
        repository.remove(mapper.map(dto, Complaint.class));
    }

    public ComplaintDTO find(Object id) {
        Complaint entity = repository.find(id);
        if (entity == null) {
            return null;
        }
        return mapper.map(entity, ComplaintDTO.class);
    }

    public List<ComplaintDTO> findAll(String status) {
        List<Complaint> entityList = repository.findAll(status);
        Type listType = new TypeToken<List<ComplaintDTO>>() {}.getType();
        List<ComplaintDTO> dtoList = mapper.map(entityList, listType);
        return dtoList;
    }
}