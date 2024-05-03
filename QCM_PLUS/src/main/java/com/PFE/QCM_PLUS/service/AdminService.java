package com.PFE.QCM_PLUS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PFE.QCM_PLUS.entity.Admin;
import com.PFE.QCM_PLUS.entity.Questionnaire;
import com.PFE.QCM_PLUS.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    public Admin updateAdmin(Long adminId, Admin adminDetails) {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();
            existingAdmin.setEmail(adminDetails.getEmail());
            existingAdmin.setNom(adminDetails.getNom());
            existingAdmin.setPrénom(adminDetails.getPrénom());
            existingAdmin.setPassword(adminDetails.getPassword());
            existingAdmin.setDate_created(adminDetails.getDate_created());
            existingAdmin.setDate_modified(adminDetails.getDate_modified());
            existingAdmin.setActive(adminDetails.isActive());
            return adminRepository.save(existingAdmin);
        } else {
            throw new RuntimeException("Admin not found with id: " + adminId);
        }
    }

    public Optional<Admin> getAdminById(Long adminId) {
        return adminRepository.findById(adminId);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public List<Questionnaire> getQuestionnairesByAdminId(Long adminId) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            return adminOptional.get().getQuestionnaires();
        } else {
            return null; 
        }
    }
}
