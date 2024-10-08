package stage_test.taskmmanagerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import stage_test.taskmmanagerapp.entities.AppRole;
import stage_test.taskmmanagerapp.entities.Person;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage_test.taskmmanagerapp.reposotries.AppRoleRep;
import stage_test.taskmmanagerapp.reposotries.PersonRep;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PersonService {
    @Autowired
    private PersonRep personRep;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppRoleRep appRoleRep;

    public Person addPerson(Person person) {
        if (personRep.findById(person.getId()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        String pwd = person.getPassword();
        person.setPassword(passwordEncoder.encode(pwd));
        return personRep.save(person);
    }

    public Person updatePerson(Person person, int id) {
        Person oldPerson = personRep.findById(id).orElse(null);
        if (oldPerson != null) {
            oldPerson.setUsername(person.getUsername());
            oldPerson.setLastName(person.getLastName());
            oldPerson.setFirstName(person.getFirstName());
            oldPerson.setPassword(person.getPassword());
            return personRep.save(oldPerson);
        }
        throw new RuntimeException("Person not found");
    }

    public void deletePersonById(int id) {
        personRep.deleteById(id);
    }

    public Person findPersonByUsername(String username) {
        Person person = personRep.findByUsername(username);
        if (person == null) {
            throw new RuntimeException("Username not found");
        }
        return personRep.findByUsername(username);
    }

    @Transactional
    public void addRoleToPerson(String username, String roleName) {
Person person = findPersonByUsername(username);
AppRole role=appRoleRep.findByRolename(roleName);
person.getRoles().add(role);
    }

}
