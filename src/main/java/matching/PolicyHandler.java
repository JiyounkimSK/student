package matching;

import matching.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired StudentRepository StudentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverVisitAssigned_(@Payload VisitAssigned visitAssigned){

        if(visitAssigned.isMe()){
            System.out.println("##### wheneverVisitAssigned_listener  : " + visitAssigned.toJson());


            StudentRepository.findById(visitAssigned.getStudent()).ifPresent(Student ->{
                System.out.println("##### MyPageRepository.findById : exist, Student:"+Student.toString() );

                //기존 학생 정보로 등록된 건이 있다면 point를 100 추가함
                Student.setPoint(Student.getPoint() + 100);
                StudentRepository.save(Student);
            });

            if(!StudentRepository.findById(visitAssigned.getStudent()).isPresent()) {

                //기존 등록된 정보가 없으면 신규등록
                System.out.println("##### MyPageRepository.findById : not exist");

                Student student = new Student();
                student.setStudent(visitAssigned.getStudent());
                student.setPoint(100);
                StudentRepository.save(student);
            }

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverVisitCanceled_(@Payload VisitCanceled visitCanceled){

        if(visitCanceled.isMe()){
            System.out.println("##### wheneverVisitAssigned_listener  : " + visitCanceled.toJson());


            StudentRepository.findById(visitCanceled.getStudent()).ifPresent(Student ->{
                System.out.println("##### MyPageRepository.findById : exist, Student:"+Student.toString() );

                //기존 학생 정보로 등록된 건이 있다면 point를 100 차감함
                if(Student.getPoint()>0){
                    Student.setPoint(Student.getPoint() - 100);
                    StudentRepository.save(Student);
                }
            });
        }
    }

}
