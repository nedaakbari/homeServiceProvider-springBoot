package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Address;
import ir.maktab.data.repository.AddressRepository;
import ir.maktab.dto.AddressDto;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final ModelMapper mapper;
    private final AddressRepository addressDao;

    @Override
    public void save(AddressDto addressDto) {
        Address address = mapper.map(addressDto,Address.class);
        addressDao.save(address);
    }

    @Override
    public void delete(AddressDto addressDto) {
        Address address = addressDao.findAddressesByZipCode(addressDto.getZipCode()).get();
        addressDao.delete(address);
    }

    @Override
    public List<AddressDto> getAll() {
        return addressDao.findAll().stream()
                .map(address -> mapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getById(Integer theId) {
        Optional<Address> found = addressDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), AddressDto.class);
        else throw new NotFoundDta("not address found");
    }

    @Override
    public AddressDto getByZipCode(String zipCode) {
        Optional<Address> found = addressDao.findAddressesByZipCode(zipCode);
        if (found.isPresent())
            return mapper.map(found.get(),AddressDto.class);
        else throw new NotFoundDta("not address found");
    }

}
