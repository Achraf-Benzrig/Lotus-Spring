package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Calendrier;

public interface ICalendrierServices {
public String addCalendrier(Calendrier c, Long userId);
public List<Calendrier> getAllCalendr();
public void deleteCalendr(Long id);
public String updateCalendr(Calendrier c);
public boolean verifCalendr(Calendrier c);
}
