package bean;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import entity.Sporocilo;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
public class SporocilaBean {

    @PersistenceContext(unitName = "sporocila-jpa")
    private EntityManager em;

    public List<Sporocilo> getSporocila(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, Sporocilo.class, queryParameters);

    }

    public Sporocilo getSporocilo(String sporociloId) {

        Sporocilo s = em.find(Sporocilo.class, sporociloId);

        if (s == null) {
            throw new NotFoundException();
        }

        return s;
    }

    public Sporocilo createSporocilo(Sporocilo s) {

        try {
            if (!em.getTransaction().isActive())
                em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        return s;
    }

    public boolean deleteSporocilo(String sporociloId) {

        Sporocilo s = em.find(Sporocilo.class, sporociloId);

        if (s != null) {
            try {
                if (!em.getTransaction().isActive())
                    em.getTransaction().begin();
                em.remove(s);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
        } else
            return false;

        return true;
    }
}
