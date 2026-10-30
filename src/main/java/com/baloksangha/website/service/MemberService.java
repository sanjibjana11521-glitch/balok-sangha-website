package com.baloksangha.website.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.baloksangha.website.entity.Member;

@Service
public class MemberService {

    private final SessionFactory sessionFactory;

    public MemberService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Member> getAllMembers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "from Member m order by m.displayOrder asc, m.name asc", Member.class)
                    .getResultList();
        }
    }
}
