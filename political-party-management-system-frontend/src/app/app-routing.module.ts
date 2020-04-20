import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './services/guards/auth-guard.service';
import { HomeComponent } from './home/home.component';
import { AdminPanelComponent } from './panels/admin-panel/admin-panel.component';
import { UserPanelComponent } from './panels/user-panel/user-panel.component';
import { ActivateAccountComponent } from './activate-account/activate-account.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { WriteArticleComponent } from './article-options/write-article/write-article.component';
import { SendMessageComponent } from './message-options/send-message/send-message.component';
import { UsersListComponent } from './users-list/users-list.component';
import { CreateTaskComponent } from './task-options/create-task/create-task.component';
import { TaskListComponent } from './task-options/task-list/task-list.component';
import { MemberPanelComponent } from './panels/member-panel/member-panel.component';
import { JoinComponent } from './join-to-organisation/join/join.component';
import { JoinMessageListComponent } from './join-to-organisation/join-message-list/join-message-list.component';
import { UnacceptableArticlesListComponent } from './article-options/unacceptable-articles-list/unacceptable-articles-list.component';
import { TaskSentListComponent } from './task-options/task-sent-list/task-sent-list.component';
import { PresidentPanelComponent } from './panels/president-panel/president-panel.component';
import { SecretaryPanelComponent } from './panels/secretary-panel/secretary-panel.component';
import { RegionPresidentPanelComponent } from './panels/region-president-panel/region-president-panel.component';
import { RegionVicePresidentPanelComponent } from './panels/region-vice-president-panel/region-vice-president-panel.component';
import { ConstituencyVicePresidentPanelComponent } from './panels/constituency-vice-president-panel/constituency-vice-president-panel.component';
import { ConstituencyPresidentPanelComponent } from './panels/constituency-president-panel/constituency-president-panel.component';
import { CreateSurveyComponent } from './survey-creator/create-survey/create-survey.component';
import { EditForPresidentComponent } from './edits/edit-for-president/edit-for-president.component';
import { EditForRegionPresidentComponent } from './edits/edit-for-region-president/edit-for-region-president.component';
import { EditForConstituencyPresidentComponent } from './edits/edit-for-constituency-president/edit-for-constituency-president.component';
import { EditForSecretaryComponent } from './edits/edit-for-secretary/edit-for-secretary.component';
import { EditForRegionSecretaryComponent } from './edits/edit-for-region-secretary/edit-for-region-secretary.component';
import { VicePresidentPanelComponent } from './panels/vice-president-panel/vice-president-panel.component';
import { EditForConstituencySecretaryComponent } from './edits/edit-for-constituency-secretary/edit-for-constituency-secretary.component';
import { RegionSecretaryPanelComponent } from './panels/region-secretary-panel/region-secretary-panel.component';
import { ConstituencySecretaryPanelComponent } from './panels/constituency-secretary-panel/constituency-secretary-panel.component';
import { MessageListComponent } from './message-options/message-list/message-list.component';
import { Survey2Component } from './survey-creator/survey2/survey2.component';
import { SurveysListComponent } from './all-survey-lists/surveys-list/surveys-list.component';
import { Survey3Component } from './survey-creator/survey3/survey3.component';
import { Survey4Component } from './survey-creator/survey4/survey4.component';
import { Survey5Component } from './survey-creator/survey5/survey5.component';
import { Survey6Component } from './survey-creator/survey6/survey6.component';
import { Survey7Component } from './survey-creator/survey7/survey7.component';
import { SurveysResultsListComponent } from './surveys-results/surveys-results-list/surveys-results-list.component';
import { ArticleRemoveComponent } from './article-options/article-remove/article-remove.component';
import { CreateStructureComponent } from './structures/create-structure/create-structure.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'rejestracja', component: RegisterComponent },
  { path: 'logowanie', component: LoginComponent },
  { path: 'admin-panel', component: AdminPanelComponent, canActivate: [AuthGuardService] },
  { path: 'president-panel', component: PresidentPanelComponent, canActivate: [AuthGuardService] },
  { path: 'vice-president-panel', component: VicePresidentPanelComponent, canActivate: [AuthGuardService] },
  { path: 'secretary-panel', component: SecretaryPanelComponent, canActivate: [AuthGuardService] },
  { path: 'region-president-panel', component: RegionPresidentPanelComponent, canActivate: [AuthGuardService] },
  { path: 'region-secretary-panel', component: RegionSecretaryPanelComponent, canActivate: [AuthGuardService] },
  { path: 'region-vice-president-panel', component: RegionVicePresidentPanelComponent, canActivate: [AuthGuardService] },
  { path: 'constituency-president-panel', component: ConstituencyPresidentPanelComponent, canActivate: [AuthGuardService] },
  { path: 'constituency-vice-president-panel', component: ConstituencyVicePresidentPanelComponent, canActivate: [AuthGuardService] },
  { path: 'constituency-secretary-panel', component: ConstituencySecretaryPanelComponent, canActivate: [AuthGuardService] },
  { path: 'user-panel', component: UserPanelComponent, canActivate: [AuthGuardService] },
  { path: 'member-panel', component: MemberPanelComponent, canActivate: [AuthGuardService] },
  { path: 'write-article', component: WriteArticleComponent, canActivate: [AuthGuardService] },
  { path: 'send-message', component: SendMessageComponent, canActivate: [AuthGuardService] },
  { path: 'join-the-party', component: JoinComponent, canActivate: [AuthGuardService] },
  { path: 'users-list', component: UsersListComponent, canActivate: [AuthGuardService] },
  { path: 'create-task', component: CreateTaskComponent, canActivate: [AuthGuardService] },
  { path: 'my-messages', component: MessageListComponent, canActivate: [AuthGuardService] },
  { path: 'my-tasks', component: TaskListComponent, canActivate: [AuthGuardService] },
  { path: 'unaccepted-articles', component: UnacceptableArticlesListComponent, canActivate: [AuthGuardService] },
  { path: 'join-message-list', component: JoinMessageListComponent, canActivate: [AuthGuardService] },
  { path: 'my-sent-tasks', component: TaskSentListComponent, canActivate: [AuthGuardService] },
  { path: 'edit-for-president', component: EditForPresidentComponent, canActivate: [AuthGuardService] },
  { path: 'edit-for-region-president', component: EditForRegionPresidentComponent, canActivate: [AuthGuardService] },
  { path: 'edit-for-constituency-president', component: EditForConstituencyPresidentComponent, canActivate: [AuthGuardService] },
  { path: 'edit-for-secretary', component: EditForSecretaryComponent, canActivate: [AuthGuardService] },
  { path: 'edit-for-region-secretary', component: EditForRegionSecretaryComponent, canActivate: [AuthGuardService] },
  { path: 'edit-for-constituency-secretary', component: EditForConstituencySecretaryComponent, canActivate: [AuthGuardService] },
  { path: 'create-survey', component: CreateSurveyComponent, canActivate: [AuthGuardService] },
  { path: 'survey2', component: Survey2Component, canActivate: [AuthGuardService] },
  { path: 'survey3', component: Survey3Component, canActivate: [AuthGuardService] },
  { path: 'survey4', component: Survey4Component, canActivate: [AuthGuardService] },
  { path: 'survey5', component: Survey5Component, canActivate: [AuthGuardService] },
  { path: 'survey6', component: Survey6Component, canActivate: [AuthGuardService] },
  { path: 'survey7', component: Survey7Component, canActivate: [AuthGuardService] },
  { path: 'surveys-list', component: SurveysListComponent, canActivate: [AuthGuardService] },
  { path: 'surveys-results', component: SurveysResultsListComponent, canActivate: [AuthGuardService] },
  { path: 'articles', component: ArticleRemoveComponent, canActivate: [AuthGuardService] },
  { path: 'create-structure', component: CreateStructureComponent, canActivate: [AuthGuardService] },
  {
    path: '',
    children: [
      {
        path: ':codeId/:activationCode',
        component: ActivateAccountComponent
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: '/nhome',
      }

    ]
  },
  { path: '**', component: PageNotFoundComponent } //Ten obiekt musi być ostatni w tablicy routingu (kolejność ma znaczenie)
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
