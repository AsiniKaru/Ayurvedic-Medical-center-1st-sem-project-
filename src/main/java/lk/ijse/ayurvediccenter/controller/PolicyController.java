package lk.ijse.ayurvediccenter.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

        public class PolicyController {

        @FXML
        private TextFlow privacyTextFlow;

        public void initialize() {
            populatePolicy();
        }

        private void populatePolicy() {
            // Clear existing content
            privacyTextFlow.getChildren().clear();

            // Title
            addText("Hela Osu Weda Gedara Management System\n", 22, true, "#344c3d");
            addText("Effective Date: January 2026\n\n", 12, false, "#7f8c8d");

            // Section 1
            addSectionTitle("1. Introduction");
            addBodyText("Hela Osu Weda Gedara is a traditional Ayurvedic clinic located in Kanampitiya, owned and operated by " +
                    "Dr. Amarasena Gurusinghe, specializing in orthopedic Ayurvedic treatments. The clinic focuses on healing the body," +
                    " mind, and soul through natural and ancestral Ayurvedic practices while integrating modern therapeutic approaches. " +
                    "The Hela Osu Weda Gedara Management System was developed to replace the clinic’s existing manual, paper-based workflow " +
                    "with a digital solution to manage patient records, diagnosis, billing, and daily operations more efficiently. " +
                    "This system was designed and developed by Asini Karunanayaka as the Final Project of the 1st Semester of a" +
                    " Software Engineering academic program. This Privacy Policy explains how information is collected, used, stored, " +
                    "and protected within the system..\n\n");

            addSectionTitle("2. Information Collected");
            addBodyText("The Hela Osu Weda Gedara Management System collects and stores essential patient information required for identification," +
                    " medical treatment, and operational purposes. This includes the patient’s full name, National Identity Card (NIC) number, " +
                    "age, contact number, residential address, and a system-generated patient ID. In addition, the system maintains " +
                    "medical-related information such as diagnosis details, treatment and medication history, prescribed medicines, " +
                    "next visit dates, and billing information to ensure continuity of care and accurate medical records. The system also " +
                    "collects limited staff information for authentication and role-based access control. This includes staff names, assigned " +
                    "roles (Doctor, Receptionist, or Assistant), and login credentials. All login credentials are securely stored to protect " +
                    "system access. Furthermore, the system records transactional and operational data related to daily clinic activities. " +
                    "This includes daily channeling records, charges for medicines and bandages, doctor fees, discounts, payment methods " +
                    "(cash or card), and daily transaction summary reports. This information is used strictly for billing accuracy, reporting, " +
                    "and efficient clinic management.\n\n");

            addSectionTitle("3. Purpose of Data Collection");
            addBodyText("The information collected through the system is used solely for: Registering and managing patient records Maintaining " +
                    "accurate patient medical history Managing daily channeling and doctor queues Recording diagnosis, treatments, and medications " +
                    "Generating accurate bills and printed receipts Accepting and tracking payments Reducing manual errors and improving clinic" +
                    " efficiency No information is collected beyond what is necessary for clinic operations.\n\n");

            addSectionTitle("4. Access Control and User Roles");
            addBodyText("Access to system data is strictly controlled based on user roles: Doctor: Can view and update patient diagnosis," +
                    "treatment records, billing details, and next visit dates Receptionist: Can register patients, manage daily channeling, " +
                    "issue medicines, accept payments, and print receipts Assistant: Can view patient records and add charges related to " +
                    "bandages and treatment materials Unauthorized access to the system is strictly prohibited.\n\n");

            addSectionTitle("5. Data Storage and Security");
            addBodyText("All data is stored in a secure and well-structured database. Access to the system requires authenticated login credentials," +
                    " and passwords are stored in encrypted form. Only authorized clinic staff can access the system according to their assigned " +
                    "roles. Regular data backups are maintained to prevent data loss and ensure system reliability.\n\n");

            addSectionTitle("6. Data Sharing and Disclosure");
            addBodyText("Patient and clinic data are not sold, shared, or disclosed to third parties. All collected information is used strictly" +
                    " for internal clinic operations. Data may only be disclosed if required by applicable laws or legal authorities.\n\n");

            addSectionTitle("7. Data Retention");
            addBodyText("Patient medical records and billing information are retained as long as necessary for treatment, reference, " +
                    "and legal purposes. Transaction records are stored for reporting and auditing needs. Data deletion or modification is allowed " +
                    "only with proper authorization.\n\n");

            addSectionTitle("8. Patient Rights");
            addBodyText("Patients have the right to: Request access to their personal and medical information Request correction of inaccurate or " +
                    "outdated information Receive clear information regarding diagnosis, treatment, and billing Such requests must be made " +
                    "through the clinic administration.\n\n");

            addSectionTitle("9. Children’s Privacy");
            addBodyText("Hela Osu Weda Gedara provides treatment for patients of all ages. Information related to minors is collected " +
                    "only for medical treatment purposes and is handled with strict confidentiality.\n\n");

            addSectionTitle("10. Academic Purpose Declaration");
            addBodyText("This system has been developed for academic purposes as the Final Project of the 1st Semester by Asini Karunanayaka." +
                    " It demonstrates practical application of software development, database management, and system design concepts " +
                    "while following ethical and responsible data-handling practices.\n\n");

            addSectionTitle("11. Policy Updates");
            addBodyText("This Privacy Policy may be updated in the future due to system improvements, academic requirements, or operational changes." +
                    " Any updates will be reflected within the system documentation.\n\n");

            addSectionTitle("12. Contact Information");
            addBodyText("For questions or concerns regarding this Privacy Policy:\n\n");
            addBodyText("Hela Osu Weda Gedara \nOwner: Dr. Amarasena Gurusinghe \nLocation: Kanampitiya\n\n");
            addBodyText("System Developer:\nAsini Karunanayaka \nFinal Project – 1st Semester (Software Engineering)\n");

        }

        private void addSectionTitle(String title) {
            addText(title + "\n", 16, true, "#293D31");
        }

        private void addBodyText(String body) {
            addText(body, 14, false, "#2c3e50");
        }

        private void addText(String content, double size, boolean bold, String hexColor) {
            Text text = new Text(content);
            text.setFont(Font.font("Arial", bold ? FontWeight.BOLD : FontWeight.NORMAL, size));
            text.setFill(Color.web(hexColor));
            privacyTextFlow.getChildren().add(text);
        }

}
