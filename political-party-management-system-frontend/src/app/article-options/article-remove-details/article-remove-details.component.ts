import { Component, OnInit, Input } from '@angular/core';
import { ArticleRemoveComponent } from '../article-remove/article-remove.component';
import { ArticleService } from 'src/app/services/article.service';
import { Article } from 'src/app/model/Article';

@Component({
  selector: 'app-article-remove-details',
  templateUrl: './article-remove-details.component.html',
  styleUrls: ['./article-remove-details.component.css']
})
export class ArticleRemoveDetailsComponent implements OnInit {

  @Input()
  article: Article;

  markup: string;
  parser = new DOMParser();
  elements: Document;

  hiddenDiv: boolean;

  constructor(private articleService: ArticleService, private articlesList: ArticleRemoveComponent) { }

  ngOnInit() {
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

}
