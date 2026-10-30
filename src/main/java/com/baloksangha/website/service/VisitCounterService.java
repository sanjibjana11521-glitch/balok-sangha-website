package com.baloksangha.website.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.baloksangha.website.entity.VisitCounter;

@Service
public class VisitCounterService {

    private final SessionFactory sessionFactory;

    public VisitCounterService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long registerVisitAndGetTotal() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // MySQL atomic upsert prevents lost increments during simultaneous visits.
                session.createNativeMutationQuery(
                    "INSERT INTO site_visit_counter (id, total_visits) VALUES (1, 1) " +
                    "ON DUPLICATE KEY UPDATE total_visits = total_visits + 1"
                ).executeUpdate();

                VisitCounter counter = session.get(VisitCounter.class, 1L);
                long total = counter.getTotalVisits();
                transaction.commit();
                return total;
            } catch (RuntimeException exception) {
                transaction.rollback();
                throw exception;
            }
        }
    }
}
