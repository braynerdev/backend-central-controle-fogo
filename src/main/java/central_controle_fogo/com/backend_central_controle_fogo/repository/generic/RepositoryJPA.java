package central_controle_fogo.com.backend_central_controle_fogo.repository.generic;

import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class RepositoryJPA<T>{
    @Autowired
    private IRepositoryJPA<T> repositoryJPA;

    public T getUserById(long id){
        return repositoryJPA.findById(id).orElse(null);
    }

    public T create(T entity){
        return repositoryJPA.save(entity);
    }

    public T deleteLogico(T entity){
        try{
            Method setActive = entity.getClass().getMethod("setActive", boolean.class);
            setActive.invoke(entity, false);
            return repositoryJPA.save(entity);
        }
        catch (Exception e){
            throw new RuntimeException ("Método setActive não encontrado ou falhou", e);
        }
    }

    public T update(T entity){
        try{
            T updated = repositoryJPA.findById(entity.getClass().getMethod("getId", Long.class));
            if (updated == null){
                return (T) ResponseDTO.newResponseDTO(false, "falha ao atualizar");
            }
            return repositoryJPA.save(entity);
        }
        catch (Exception e){
            return new RuntimeException ("Método getId não encontrado ou falhou", e);
        }
    }
}
