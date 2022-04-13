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


    public PersonDataService(PersonDataRepository personDataRepository,  ModelMapper modelMapper) {
        this.personDataRepository = personDataRepository;
        this.modelMapper = modelMapper;

    }

    public List<PersonDataDto> findAll() {
        List<PersonDataEntity> personDataList = personDataRepository.findAll();
        return personDataList.stream().map(el -> modelMapper.map(el, PersonDataDto.class)).collect(Collectors.toList());
    }

    public PersonDataDto findById(Long id) {
        PersonDataEntity personData = personDataRepository.findById(id);
        return modelMapper.map(personData, PersonDataDto.class);
    }

    public PersonDataDto findByName(String name) {
        PersonDataEntity personData = personDataRepository.findByName(name);
        return modelMapper.map(personData, PersonDataDto.class);
    }


    public void updateById(PersonDataEntity personDataEntity) {

        personDataRepository.updateById(personDataEntity);
    }

    public void insert(PersonDataDto personDataDto) {
        PersonDataEntity personData = modelMapper.map(personDataDto, PersonDataEntity.class);
            personDataRepository.insert(personData);
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
