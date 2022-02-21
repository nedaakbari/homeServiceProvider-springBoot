package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Person.Admin;
import ir.maktab.data.enums.Role;
import ir.maktab.data.repository.AdminRepository;
import ir.maktab.dto.AdminDto;
import ir.maktab.service.exception.IncorrectInformation;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ModelMapper mapper;
    private final AdminRepository adminDao;

    @Override
    public void save(AdminDto adminDto) {
        Admin admin = mapper.map(adminDto, Admin.class);
        adminDto.setRole(Role.ADMIN);
        adminDao.save(admin);
    }

    @Override
    public AdminDto login(AdminDto adminDto) {
        Optional<Admin> admin = adminDao.findByEmail(adminDto.getEmail());
        if (admin.isPresent()) {
            return mapper.map(admin.get(), AdminDto.class);
        } else
            throw new NotFoundDta("no user found with these info");
    }

    @Override
    public void delete(AdminDto adminDto) {
        Optional<Admin> admin = adminDao.findByEmail(adminDto.getEmail());
        if (admin.isPresent())
            adminDao.delete(admin.get());
        else throw new NotFoundDta("no admin found to delete");
    }

    @Override
    public List<AdminDto> getAll() {
        return adminDao.findAll().stream()
                .map(admin -> mapper.map(admin, AdminDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdminDto getById(Integer theId) {
        Optional<Admin> found = adminDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), AdminDto.class);
        else throw new NotFoundDta("no admin found ");
    }

    @Override
    public AdminDto findAminByUseAndPass(String username, String password) {
        Optional<Admin> admin = adminDao.findByUsernameAndPassword(username, password);
        if (admin.isPresent()) {
            return mapper.map(admin.get(), AdminDto.class);
        } else
            throw new NotFoundDta("no admin found with these use and pass");
    }

    @Override
    public Admin findAminByEmail(String email) {
        Optional<Admin> admin = adminDao.findByEmail(email);
        if (admin.isPresent()) {
            return admin.get();
        } else
            throw new NotFoundDta("no admin found with these email");
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, AdminDto adminDto) {
        Admin admin = mapper.map(adminDto, Admin.class);
        if (oldPassword.equals(admin.getPassword())) {
            admin.setPassword(newPassword);
            adminDao.save(admin);
        } else
            throw new IncorrectInformation("incorrect passWord");

    }

    @Override
    public void update(AdminDto adminDto) {
        Optional<Admin> admin = adminDao.findByEmail(adminDto.getEmail());
        adminDao.save(admin.get());
    }

    @Override
    public boolean isExist(String username, String password) {
        Optional<Admin> found = adminDao.findByUsernameAndPassword(username, password);
        if (found.isPresent())
            return true;
        else return false;
    }

}
