import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Survey2 } from 'src/app/model/surveys/survey2';
import { SurveyService } from 'src/app/services/survey.service';
import { Survey3 } from 'src/app/model/surveys/Survey3';
import { Survey4 } from 'src/app/model/surveys/Survey4';
import { Survey5 } from 'src/app/model/surveys/Survey5';
import { Survey6 } from 'src/app/model/surveys/Survey6';
import { Survey7 } from 'src/app/model/surveys/Survey7';

@Component({
  selector: 'app-surveys-list',
  templateUrl: './surveys-list.component.html',
  styleUrls: ['./surveys-list.component.css']
})
export class SurveysListComponent implements OnInit {

  surveys2: Observable<Survey2[]>;
  surveys3: Observable<Survey3[]>;
  surveys4: Observable<Survey4[]>;
  surveys5: Observable<Survey5[]>;
  surveys6: Observable<Survey6[]>;
  surveys7: Observable<Survey7[]>;

  constructor(private surveyService: SurveyService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.surveys2 = this.surveyService.getSurveys2ToVote();
    this.surveys3 = this.surveyService.getSurveys3ToVote();
    this.surveys4 = this.surveyService.getSurveys4ToVote();
    this.surveys5 = this.surveyService.getSurveys5ToVote();
    this.surveys6 = this.surveyService.getSurveys6ToVote();
    this.surveys7 = this.surveyService.getSurveys7ToVote();
  }

}
