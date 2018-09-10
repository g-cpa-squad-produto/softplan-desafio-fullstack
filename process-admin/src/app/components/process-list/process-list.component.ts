import { Component, OnInit } from "@angular/core";
import { SharedService } from "../../services/shared.service";
import { Process } from "../../model/process.model";
import { DialogService } from "../../dialog.service";
import { Router } from "@angular/router";
import { ProcessService } from "../../services/process.service";
import { ResponseApi } from "../../model/response-api";

@Component({
  selector: "app-process-list",
  templateUrl: "./process-list.component.html",
  styleUrls: ["./process-list.component.css"]
})
export class ProcessListComponent implements OnInit {
  assignedToMe: boolean = false;
  page: number = 0;
  count: number = 10;
  pages: Array<number>;
  shared: SharedService;
  message: {};
  classCss: {};
  processes: Array<Process>;
  processFilter = new Process("", null, "", "", "", null, null, "", null);

  constructor(
    private dialogService: DialogService,
    private processService: ProcessService,
    private router: Router
  ) {
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.findAll(this.page, this.count);
  }

  findAll(page, count: number) {
    this.processService.findAll(page, count).subscribe(
      (responseApi: ResponseApi) => {
        this.processes = responseApi["data"]["content"];
        this.pages = new Array(responseApi["data"]["totalPages"]);
      },
      err => {
        this.showMessage({
          type: "error",
          text: err["error"]["errors"][0]
        });
      }
    );
  }

  filter(): void {
    this.page = 0;
    this.count = 10;
    let filterParams = Object.assign({}, this.processFilter);

    this.processService
      .findByParams(
        this.page,
        this.count,
        this.assignedToMe,
        filterParams
      )
      .subscribe(
        (responseApi: ResponseApi) => {          
          this.processes = responseApi["data"]["content"];
          this.pages = new Array(responseApi["data"]["totalPages"]);
        },
        err => {
          this.showMessage({
            type: "error",
            text: err["error"]["errors"][0]
          });
        }
      );
  }

  cleanFilter(): void {
    this.assignedToMe = false;
    this.page = 0;
    this.count = 10;
    this.processFilter = new Process("", null, "", "", "", null, null, "", null);
    this.findAll(this.page, this.count);
  }

  edit(id: string) {
    this.router.navigate(['/process-new', id]);
  }

  detail(id: string){
    this.router.navigate(['/process-detail', id]);
  }

  delete(id: string) {
    this.dialogService
      .confirm("Deseja excluir este processo?")
      .then((canDelete: boolean) => {
        if (canDelete) {
          this.message = {};
          this.processService.delete(id).subscribe(
            (responseApi: ResponseApi) => {
              this.showMessage({
                type: "success",
                text: "Registro excluído"
              });
              this.findAll(this.page, this.count);
            },
            err => {
              this.showMessage({
                type: "error",
                text: err["error"]["errors"][0]
              });
            }
          );
        }
      });
  }

  setNextPage(event: any) {
    event.preventDefault();

    if (this.page + 1 < this.pages.length) {
      this.page++;
      this.findAll(this.page, this.count);
    }
  }

  setPreviousPage(event: any) {
    event.preventDefault();

    if (this.page > 0) {
      this.page--;
      this.findAll(this.page, this.count);
    }
  }

  setPage(i, event: any) {
    event.preventDefault();
    this.page = i;
    this.findAll(this.page, this.count);
  }

  showMessage(message: { type: string; text: string }): void {
    this.message = message;
    this.buildClasses(message.type);
    setTimeout(() => {
      this.message = undefined;
    }, 3000);
  }

  private buildClasses(type: string): void {
    this.classCss = {
      alert: true
    };
    this.classCss["alert-" + type] = true;
  }
}
