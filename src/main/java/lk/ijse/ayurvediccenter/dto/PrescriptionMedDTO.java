package lk.ijse.ayurvediccenter.dto;

public class PrescriptionMedDTO {
        private int medId;
        private String medName;
        private int medQty;
        private String instruction;

        public PrescriptionMedDTO() {
        }

        public PrescriptionMedDTO(String medName, int medQty, String instruction) {
            this.medName = medName;
            this.medQty = medQty;
            this.instruction = instruction;
        }

        public PrescriptionMedDTO(int medId, String medName, int medQty, String instruction) {
            this.medId = medId;
            this.medName = medName;
            this.medQty = medQty;
            this.instruction = instruction;
        }

        public int getMedId() {
            return medId;
        }

        public void setMedId(int medId) {
            this.medId = medId;
        }

        public String getMedName() {
            return medName;
        }

        public void setMedName(String medName) {
            this.medName = medName;
        }

        public int getMedQty() {
            return medQty;
        }

        public void setMedQty(int medQty) {
            this.medQty = medQty;
        }

        public String getInstruction() {
            return instruction;
        }

        public void setInstruction(String instruction) {
            this.instruction = instruction;
        }

        @Override
        public String toString() {
            return "MedPrescriptionTM{" +
                    "medId=" + medId +
                    ", medName='" + medName + '\'' +
                    ", medQty='" + medQty + '\'' +
                    ", instruction='" + instruction + '\'' +
                    '}';
        }


}
