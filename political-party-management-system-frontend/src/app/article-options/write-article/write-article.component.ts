import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-write-article',
  templateUrl: './write-article.component.html',
  styleUrls: ['./write-article.component.css']
})
export class WriteArticleComponent implements OnInit {

  title: string;

  htmlContent = '';

  editorConfig = {
    editable: true,
    spellcheck: false,
    height: '20rem',
    minHeight: '5rem',
    placeholder: 'Napisz artykuł...',
    translate: 'no',
  };

  constructor(private articleService: ArticleService, private toastService: ToastrService) { }

  ngOnInit() {
    this.title = '';
    this.htmlContent = '';
  }

  sendArticle() {
    this.articleService.createArticle( {title: this.title, content: this.htmlContent} ).subscribe(
      data => {
        console.log(data);
        this.title = '';
        this.htmlContent = '';
        this.showSuccessAlert();
      },
      error => console.log(error));
  }

  showSuccessAlert() {
    this.toastService.success('Pomyślnie dodano nowy artykuł!', 'SUKCES!');
  }

}
