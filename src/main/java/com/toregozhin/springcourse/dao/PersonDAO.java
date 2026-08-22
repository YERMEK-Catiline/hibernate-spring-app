package com.toregozhin.springcourse.dao;

import com.toregozhin.springcourse.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Person", Person.class).getResultList();
    }
    
    @Transactional
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        return person;
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        person.setName(updatePerson.getName());
        person.setAge(updatePerson.getAge());
        person.setEmail(updatePerson.getEmail());

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        session.remove(person);

    }
}
