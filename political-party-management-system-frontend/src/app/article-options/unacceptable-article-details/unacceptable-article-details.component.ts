import { Component, OnInit, Input } from '@angular/core';
import { UnacceptableArticlesListComponent } from '../unacceptable-articles-list/unacceptable-articles-list.component';
import { Article } from 'src/app/model/Article';
import { ArticleService } from 'src/app/services/article.service';


@Component({
  selector: 'app-unacceptable-article-details',
  templateUrl: './unacceptable-article-details.component.html',
  styleUrls: ['./unacceptable-article-details.component.css']
})
export class UnacceptableArticleDetailsComponent implements OnInit {

  @Input()
  article: Article;

  markup: string;
  parser = new DOMParser();
  elements: Document;

  hiddenDiv: boolean;

  constructor(private articleService: ArticleService, private uacceptableArticlesList: UnacceptableArticlesListComponent) { }

  ngOnInit() {
    this.hiddenDiv = false;
    this.markup = this.article.content;
    this.elements = this.parser.parseFromString(this.markup, 'text/xml');
  }

  removeArticle() {
    this.hiddenDiv = true;
    this.articleService.deleteArticle(this.article.id).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

  accept() {
    this.hiddenDiv = true;
    this.articleService.acceptArticle(this.article.id, this.article).subscribe(
      data => {
        console.log(data);
      }, error => console.log(error)
    );
  }

}
