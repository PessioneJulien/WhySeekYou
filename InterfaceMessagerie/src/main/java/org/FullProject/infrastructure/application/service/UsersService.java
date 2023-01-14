package org.FullProject.infrastructure.application.service;

import lombok.Data;
import org.FullProject.infrastructure.application.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.FullProject.infrastructure.application.model.Users;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Data
@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;


    public Users saveUsers(Users users) {
        Users savedUsers = usersRepository.save(users);
        return savedUsers;
    }

    public void deleteUsers(final Long id) {
        usersRepository.deleteById(id);
    }

    public Optional<Users> getUsers(final Long id) {
        return usersRepository.findById(id);
    }

    public List<String> getUsersBis(final Long id) {
        List<String> list = new ArrayList<String>();
        list.add(usersRepository.findById(id).get().getNom());
        list.add(usersRepository.findById(id).get().getMail());
        return list;
    }

    public Optional<Users> getUsersMail(final String email) {
        Iterable<Users> Allusers = usersRepository.findAll();
        for (Users users : Allusers) {
            if (users.getMail().equals(email)) {
                return usersRepository.findById(users.getId());
            }
        }
        return null;
    }
    public List<String> getUserMailBis(final String email) {
        List<String> list = new ArrayList<String>();
        Iterable<Users> Allusers = usersRepository.findAll();
        for (Users users : Allusers) {
            if (users.getMail().equals(email)) {
                list.add(users.getNom());
                list.add(users.getMail());
            }
        }
        return list;
    }

    public List<Users> getUsersRelationAmis (Long idDemandeur, Long idReveceur) {
        List<Users> listUsers = new ArrayList<Users>();
        listUsers.add(getUsers(idDemandeur).get());
        listUsers.add(getUsers(idReveceur).get());
        return listUsers;
    }

    public Long connexion(String email, String password) {
        Users user = getUsersMail(email).get();
        if (user.getPassword().equals(password)) {
            return user.getId();
        }
        return null;
    }
}
