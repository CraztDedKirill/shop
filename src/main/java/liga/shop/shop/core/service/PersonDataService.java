package liga.shop.shop.core.service;

import liga.shop.shop.core.model.PersonDataEntity;
import liga.shop.shop.core.repository.PersonDataRepository;
import liga.shop.shop.dto.PersonDataDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonDataService implements UserDetailsService {

    private PersonDataRepository personDataRepository;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    public PersonDataService(PersonDataRepository repository, ModelMapper modelMapper) {
        this.personDataRepository = repository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    public List<PersonDataDto> findAll() {
        List<PersonDataEntity> personDataList = personDataRepository.findAll();
        return personDataList.stream().map(el -> modelMapper.map(el, PersonDataDto.class)).collect(Collectors.toList());
    }

    public PersonDataDto findById(Long id) {
        PersonDataEntity personData = personDataRepository.findById(id);
        return modelMapper.map(personData, PersonDataDto.class);
    }


    //  @Override
    //  public void updateById(PersonDataDto personDataDto) {
    //      PersonDataEntity personData = modelMapper.map(personDataDto, PersonDataEntity.class);
    //      personDataRepository.updateById(personData);
    //  }

    public void insert(PersonDataDto personDataDto) {
        PersonDataEntity personData = modelMapper.map(personDataDto, PersonDataEntity.class);
        if (personData.getId() == null)
            personDataRepository.insert(personData);
        //       else
        //           personDataRepository.updateById(personData);
    }

    public void insertUser(PersonDataEntity personDataEntity) {
        personDataRepository.insert(personDataEntity);
    }

    public void deleteById(Long id) {
        personDataRepository.deleteById(id);
    }

    public PersonDataEntity findByEmail(String email) {
        return personDataRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PersonDataEntity personData = findByEmail(email);
        return personData;
    }

}