package com.distribuida.repo;

import com.distribuida.db.SingerInstrument;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class SingerInstrumentRepo {

    @PersistenceContext(unitName = "servicio3")
    EntityManager em;

    public List<SingerInstrument> findAll() {
        return em.createQuery("SELECT s FROM SingerInstrument s", SingerInstrument.class).getResultList();
    }

    public SingerInstrument findById(Integer id) {
        return em.find(SingerInstrument.class, id);
    }

    public SingerInstrument create(SingerInstrument singerInstrument) {
        em.persist(singerInstrument);
        return singerInstrument;
    }

    public SingerInstrument update(SingerInstrument singerInstrument) {
        return em.merge(singerInstrument);
    }

    public void delete(Integer id) {
        SingerInstrument singerInstrument = em.find(SingerInstrument.class, id);
        em.remove(singerInstrument);
    }

    //find by Singer id
    public List<SingerInstrument> findBySingerId(Integer id) {
        return em.createQuery("SELECT s FROM SingerInstrument s WHERE s.singerId = :id", SingerInstrument.class)
                .setParameter("id", id)
                .getResultList();
    }

    //find by Instrument id
    public List<SingerInstrument> findByInstrumentId(Integer id) {
        return em.createQuery("SELECT s FROM SingerInstrument s WHERE s.instrumentId = :id", SingerInstrument.class)
                .setParameter("id", id)
                .getResultList();
    }




}
