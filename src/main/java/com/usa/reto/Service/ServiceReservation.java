package com.usa.reto.Service;

import com.usa.reto.Model.Reservation;
import com.usa.reto.Repository.RepositoryReservation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Isaias Palacio
 */
@Service
public class ServiceReservation {

    /**
     * creación de variable de tipo Repositorio con la anotación
     */
    @Autowired
    private RepositoryReservation repository;
    
    /**
     * Metodo para obtener todos los datos de la tabla Reservation
     *
     * @return List de clase Reservation
     */
    public List<Reservation> getAll() {
        return repository.getAll();
    }
    
    
    /**
     * metodo para obtener dato de la tabla Reservation por Id
     *
     * @param id
     * @return Optional de clase Reservacion
     */
    public Optional<Reservation> getReservation(int id){
        return repository.getReservation(id);
    }
    
    /**
     * metodo para registrar valores en la tabla Reservation
     *
     * @param r
     * @return valor de clase Reservacion
     */
    public Reservation save(Reservation r){
        if(r.getIdReservation() == null){
            return repository.save(r);
        }else{
            Optional<Reservation> rAux = repository.getReservation(r.getIdReservation());
            if(rAux.isEmpty()){
                return repository.save(r);
            }else{
                return r;
            }
        }
    }
    
    /**
     * metodo para actualizar un dato de la tabla Reservation
     * @param reservation
     * @return valor de calse Reservacion
     */
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> rAux = repository.getReservation(reservation.getIdReservation());
            if(!rAux.isEmpty()){

                if(reservation.getStartDate()!=null){
                    rAux.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    rAux.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    rAux.get().setStatus(reservation.getStatus());
                }
                repository.save(rAux.get());
                return rAux.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }
    
    /**
     * metodo para borrar un dato de la tabla Reservation por Id
     *
     * @param id
     * @return boolean
     */
    public boolean delete(int id) {
        Boolean aBoolean = getReservation(id).map(reservation -> {
            repository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}